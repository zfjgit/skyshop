/**
 * 
 */
package com.sitv.skyshop.dao.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
@MappedTypes(Calendar.class)
@MappedJdbcTypes(value = JdbcType.TIMESTAMP, includeNullJdbcType = true)
public class CalendarTypeHandler implements TypeHandler<Calendar> {

	public Calendar getResult(ResultSet rs, String name) throws SQLException {
		Timestamp t = rs.getTimestamp(name);
		if(t == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		return c;
	}

	public Calendar getResult(ResultSet rs, int idx) throws SQLException {
		Timestamp t = rs.getTimestamp(idx);
		if(t == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		return c;
	}

	public Calendar getResult(CallableStatement cs, int idx) throws SQLException {
		Timestamp t = cs.getTimestamp(idx);
		if(t == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		return c;
	}

	public void setParameter(PreparedStatement ps, int idx, Calendar t, JdbcType jdbcType) throws SQLException {
		if(t == null) {
			return;
		}
		ps.setTimestamp(idx, new Timestamp(t.getTimeInMillis()));
	}
}
