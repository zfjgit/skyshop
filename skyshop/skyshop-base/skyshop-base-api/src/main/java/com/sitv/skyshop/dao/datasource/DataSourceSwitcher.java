package com.sitv.skyshop.dao.datasource;
import org.springframework.util.Assert;

public class DataSourceSwitcher {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSource(String dataSource) {
		Assert.notNull(dataSource, "dataSource cannot be null");
		contextHolder.set(dataSource);
	}

	public static void setWrite(){
		clearDataSource();
	}

	public static void setRead() {
		setDataSource("read");
	}

	public static String getDataSource() {
		return contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}

