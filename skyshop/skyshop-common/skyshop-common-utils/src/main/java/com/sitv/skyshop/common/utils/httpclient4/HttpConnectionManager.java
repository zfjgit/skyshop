package com.sitv.skyshop.common.utils.httpclient4;

import javax.annotation.PostConstruct;

import org.apache.http.client.config.RequestConfig;
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
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpConnectionManager {

	private static final Logger log = LoggerFactory.getLogger(HttpConnectionManager.class);
	
	/**
	 * 最大连接数
	 */
	public final static int MAX_TOTAL_CONNECTIONS = 800;
	/**
	 * 获取连接的最大等待时间
	 */
	public final static int WAIT_TIMEOUT = 60000;
	/**
	 * 每个路由最大连接数
	 */
	public final static int MAX_ROUTE_CONNECTIONS = 400;
	/**
	 * 连接超时时间
	 */
	public final static int CONNECT_TIMEOUT = 10000;
	/**
	 * 读取超时时间
	 */
	public final static int READ_TIMEOUT = 10000;

	// 池化管理
	private PoolingHttpClientConnectionManager poolConnManager = null;

	//private static CloseableHttpClient httpClient;

	// 请求器的配置
	private RequestConfig requestConfig;
	
	
	@PostConstruct
	public void init() {
		try {
			log.info("初始化poolConnManager");

			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());

			// 配置同时支持 HTTP 和 HTPPS
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
			                .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
			// 初始化连接管理器
			poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

			// 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
			poolConnManager.setMaxTotal(200);

			// 设置最大路由
			poolConnManager.setDefaultMaxPerRoute(20);

			// 根据默认超时限制初始化requestConfig
			int socketTimeout = 10000;
			int connectTimeout = 10000;
			int connectionRequestTimeout = 10000;

			requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

			// 初始化httpClient
			//httpClient = getConnection();

			log.info("初始化poolConnManager~~~结束");
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public CloseableHttpClient getConnection() {
		CloseableHttpClient httpClient = HttpClients.custom()
		                // 设置连接池管理
		                .setConnectionManager(poolConnManager)
		                // 设置请求配置
		                .setDefaultRequestConfig(requestConfig)
		                // 设置重试次数
		                .setRetryHandler(new DefaultHttpRequestRetryHandler(1, false)).build();
		if (poolConnManager != null && poolConnManager.getTotalStats() != null) {
			log.info("now client pool " + poolConnManager.getTotalStats().toString());
		}

		return httpClient;
	}
}