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

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20
 * @version 2017年8月5日
 */
@Slf4j
@MappedTypes(Calendar.class)
@MappedJdbcTypes(value = JdbcType.TIMESTAMP, includeNullJdbcType = true)
public class CalendarTypeHandler implements TypeHandler<Calendar> {

	public Calendar getResult(ResultSet rs, String name) throws SQLException {
		log.debug("Calendar Converter....name=" + name);
		Timestamp t = rs.getTimestamp(name);
		log.debug("t=" + t);
		if (t == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		return c;
	}

	public Calendar getResult(ResultSet rs, int idx) throws SQLException {
		log.debug("Calendar Converter....idx=" + idx);
		Timestamp t = rs.getTimestamp(idx);
		log.debug("t=" + t);
		if (t == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		return c;
	}

	public Calendar getResult(CallableStatement cs, int idx) throws SQLException {
		log.debug("Calendar Converter..CallableStatement..idx=" + idx);
		Timestamp t = cs.getTimestamp(idx);
		log.debug("t=" + t);
		if (t == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(t.getTime());
		return c;
	}

	public void setParameter(PreparedStatement ps, int idx, Calendar t, JdbcType jdbcType) throws SQLException {
		log.debug("Calendar Converter....setParameter=" + idx);
		if (t == null) {
			return;
		}
		log.debug("t=" + t);
		ps.setTimestamp(idx, new Timestamp(t.getTimeInMillis()));
	}
}
