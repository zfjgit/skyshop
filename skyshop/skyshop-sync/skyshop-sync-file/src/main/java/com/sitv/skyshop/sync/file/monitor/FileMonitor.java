/**
 * 
 */
package com.sitv.skyshop.sync.file.monitor;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author zfj20 @ 2017年9月18日
 */
@Component
public class FileMonitor {
	
	private static final Logger log = LoggerFactory.getLogger(FileMonitor.class);

	@Autowired
	private FileListener listener;
	
	@Autowired
	private Environment env;
	
	@PostConstruct
	public void start() throws Exception {
		// 监控目录
		String rootDir = env.getProperty("file.monitor.dir");

		// 轮询间隔 5 秒
		long interval = TimeUnit.SECONDS.toMillis(2);

		// 创建一个文件观察器用于处理文件的格式
		FileAlterationObserver observer = new FileAlterationObserver(rootDir,
		                FileFilterUtils.or(
		                				FileFilterUtils.fileFileFilter(), 
		                				FileFilterUtils.directoryFileFilter(), 
		                				FileFilterUtils.suffixFileFilter(".jpg"), 
		                				FileFilterUtils.suffixFileFilter(".gif"),
		                                FileFilterUtils.suffixFileFilter(".jpeg"), 
		                                FileFilterUtils.suffixFileFilter(".png"), 
		                                FileFilterUtils.suffixFileFilter(".ico"),
		                                FileFilterUtils.suffixFileFilter(".bmp")
		                				), 
		                null);

		observer.addListener(listener); // 设置文件变化监听器

		// 创建文件变化监听器
		FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);

		log.info("start monitor.....");
		
		// 开始监控
		monitor.start();
	}
}
