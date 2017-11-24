/**
 *
 */
package com.sitv.skyshop.sync.file.monitor;

import java.io.File;
import java.net.URLEncoder;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sitv.skyshop.common.utils.httpclient4.HttpConnectionManager;

/**
 * @author zfj20 @ 2017年9月18日
 */
@Component
public class FileListener extends FileAlterationListenerAdaptor {

	private static final Logger log = LoggerFactory.getLogger(FileListener.class);

	@Autowired
	private HttpConnectionManager httpManager;

	@Autowired
	private Environment env;

	public void onFileCreate(File file) {
		log.info("[新建]:" + file.getAbsolutePath());

		try {
			HttpPost request = new HttpPost(env.getProperty("file.receiver.url"));

			String monitorDir = env.getProperty("file.monitor.dir");

			log.info("monitordir=" + monitorDir);

			FileBody fileBody = new FileBody(file);

			String fileName = file.getAbsolutePath().replace(monitorDir, "");
			// fileName = new String(fileName.getBytes(), "utf-8");
			fileName = URLEncoder.encode(fileName, "utf-8");

			log.info("fileName=" + fileName);

			StringBody fileNameBody = new StringBody(fileName, ContentType.create("text/plain", "UTF-8"));

			HttpEntity httpEntity = MultipartEntityBuilder.create().addPart("file", fileBody).addPart("fileName", fileNameBody).build();
			request.setEntity(httpEntity);

			HttpClient client = httpManager.getConnection();

			HttpResponse httpResponse = client.execute(request);

			String r = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
			log.info("r=" + r);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void onFileChange(File file) {
		log.info("[修改]:" + file.getAbsolutePath());
	}

	public void onFileDelete(File file) {
		log.info("[删除]:" + file.getAbsolutePath());
	}

	public void onDirectoryCreate(File directory) {
		log.info("[新建]:" + directory.getAbsolutePath());

	}

	public void onDirectoryChange(File directory) {
		log.info("[修改]:" + directory.getAbsolutePath());
	}

	public void onDirectoryDelete(File directory) {
		log.info("[删除]:" + directory.getAbsolutePath());
	}

	public void onStart(FileAlterationObserver observer) {
		super.onStart(observer);
	}

	public void onStop(FileAlterationObserver observer) {
		super.onStop(observer);
	}

}
