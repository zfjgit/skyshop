/**
 * 
 */
package com.sitv.skyshop.sync.receiverdata.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;



/**
 * 
 * @author zfj20 @ 2017年9月16日
 */
@Service("mysqlService")
public class MySQLService implements IUpdateService {

	private static final Logger log = LoggerFactory.getLogger(MySQLService.class);
	
	@Autowired
	@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate mysqlJdbcTemplate;
	
	public void update(JSONObject sqlObject) {
		String sqlStr = sqlObject.getString("sql");
		JSONArray params = sqlObject.getJSONArray("params");
		
		if(StringUtils.isBlank(sqlStr)) {
			return;
		}
		
		String r1 = mysqlJdbcTemplate.execute(sqlStr, new PreparedStatementCallback<String>() {
			public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				if(params != null) {
					for (int i = 0; i < params.length(); i++) {
						Object p = params.get(i);
						ps.setObject(i + 1, p);
					}
				}
				ps.executeUpdate();
				return OK;
			}
		});
		
		log.info("r1=" + r1);
	}
}
