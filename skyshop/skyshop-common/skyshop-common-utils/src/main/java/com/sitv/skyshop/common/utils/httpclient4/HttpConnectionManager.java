package com.sitv.skyshop.common.utils.httpclient4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HttpConnectionManager {

	private static final Logger log = LoggerFactory.getLogger(HttpConnectionManager.class);

	@Autowired
	private Environment environment;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PoolingHttpClientConnectionManager poolManager() {
		SSLContextBuilder builder = new SSLContextBuilder();
		SSLConnectionSocketFactory sslsf = null;
		try {
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			sslsf = new SSLConnectionSocketFactory(builder.build());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
		if (sslsf == null) {
			socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).build();
		} else {
			// 配置同时支持 HTTP 和 HTPPS
			socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf)
			                .build();
		}
		// 初始化连接管理器
		PoolingHttpClientConnectionManager poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

		// 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
		poolConnManager.setMaxTotal(200);

		// 设置最大路由
		poolConnManager.setDefaultMaxPerRoute(20);

		return poolConnManager;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RequestConfig requestConfig() {
		int socketTimeout = Integer.parseInt(environment.getProperty(Constants.HTTP4_SOCKET_TIMEOUT_MILLILSEONDS).trim());
		int connectTimeout = Integer.parseInt(environment.getProperty(Constants.HTTP4_CONNECTION_TIMEOUT_MILLILSEONDS).trim());
		int connectionRequestTimeout = Integer.parseInt(environment.getProperty(Constants.HTTP4_REQUEST_TIMEOUT_MILLILSEONDS).trim());

		return RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CloseableHttpClient getConnection() {
		RequestConfig requestConfig = requestConfig();
		PoolingHttpClientConnectionManager poolConnManager = poolManager();
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolConnManager).setDefaultRequestConfig(requestConfig)
		                .setRetryHandler(new DefaultHttpRequestRetryHandler(2, false)).build();
		if (poolConnManager != null && poolConnManager.getTotalStats() != null) {
			log.info("now client pool " + poolConnManager.getTotalStats().toString());
		}

		return httpClient;
	}

	public String doHttpGet(String uri) {
		log.debug("GET调用外部接口>>>");
		log.debug("uri=" + uri);
		BufferedReader reader = null;
		try {
			if (Utils.isNull(uri)) {
				return null;
			}
			HttpGet request = new HttpGet(uri);
			CloseableHttpClient client = getConnection();
			CloseableHttpResponse response = client.execute(request);
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String result = "";
			String line = null;
			while ((line = reader.readLine()) != null) {
				result += line;
			}
			log.debug("result=" + result);
			return result;
		} catch (Exception e) {
			log.error("GET调用外部接口失败", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	public String doHttpPost(String uri, Map<String, Object> params) {
		log.debug("POST调用外部接口>>>");
		log.debug("uri=" + uri);
		log.debug("params=" + new JSONObject(params));
		BufferedReader reader = null;
		try {
			if (Utils.isNull(uri)) {
				return null;
			}
			List<NameValuePair> parameters = new ArrayList<>();
			if (params != null) {
				for (Map.Entry<String, Object> param : params.entrySet()) {
					parameters.add(new BasicNameValuePair(param.getKey(), param.getValue() + ""));
				}
			}
			HttpEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");
			HttpPost request = new HttpPost(uri);
			request.setEntity(entity);
			CloseableHttpResponse response = getConnection().execute(request);
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			String result = "";
			String line = null;
			while ((line = reader.readLine()) != null) {
				result += line;
			}
			log.debug("result=" + result);
			return result;
		} catch (Exception e) {
			log.error("POST调用外部接口失败", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}
}