package com.sitv.skyshop.sync.data.canal;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

/**
 * 集群模式
 *
 * @author jianghang 2013-4-15 下午04:19:20
 * @version 1.0.4
 */
public class ClusterCanalClient extends AbstractCanalClient implements ServletContextListener {

	public ClusterCanalClient() {
		this(null);
	}

	public ClusterCanalClient(String destination) {
		super(destination);
	}

	public void run() {
		String destination = "example";

		String zkServers = environment.getProperty("canal.cluster.zkServers");
		if (StringUtils.isBlank(zkServers)) {
			logger.info("cluster canal client start failured!!!");
			return;
		}

		CanalConnector connector = CanalConnectors.newClusterConnector(zkServers, destination, "", "");

		final ClusterCanalClient clientTest = this;
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

	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("ClusterCanalClient Destroyed!!!");
		stop();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("ClusterCanalClient Initialized!!!");
		this.run();
	}
}
