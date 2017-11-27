package com.sitv.skyshop.dao.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	protected Object determineCurrentLookupKey() {
		return DataSourceSwitcher.getDataSource();
	}

}

