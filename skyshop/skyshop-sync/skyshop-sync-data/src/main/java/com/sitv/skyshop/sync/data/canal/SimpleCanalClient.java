package com.sitv.skyshop.sync.data.canal;

import java.net.InetSocketAddress;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

/**
 * 单机模式
 *
 * @author jianghang 2013-4-15 下午04:19:20
 * @version 1.0.4
 */
public class SimpleCanalClient extends AbstractCanalClient implements ServletContextListener {

	public SimpleCanalClient() {
		this(null);
	}

	public SimpleCanalClient(String destination) {
		super(destination);
	}

	public void run() {
		// 根据ip，直接创建链接，无HA的功能
		String destination = "example";

		String canalServer = environment.getProperty("canal.single.canalServer");
		if (StringUtils.isBlank(canalServer)) {
			logger.info("simple canal client start failured!!!");
			return;
		}

		int port = environment.getProperty("canal.single.port", Integer.class);

		CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(canalServer, port), destination, "", "");

		final SimpleCanalClient clientTest = this;
		clientTest.setDestination(destination);
		clientTest.setConnector(connector);
		clientTest.start();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					logger.info("## stop the canal client");
					clientTest.stop();
				} catch (Throwable e) {
					logger.warn("##something goes wrong when stopping canal:\n{}", ExceptionUtils.getFullStackTrace(e));
				} finally {
					logger.info("## canal client is down.");
				}
			}
		});
	}

	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("SimpleCanalClient Destroyed!!!");
		stop();
	}

	public void contextInitialized(ServletContextEvent sce) {
		logger.info("SimpleCanalClient Initialized!!!");
		this.run();
	}

}
