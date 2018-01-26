/**
 *
 */
package com.sitv.skyshop.wisdomedu.dao.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * @author zfj20
 */
public class BooleanTypeHandler extends BaseTypeHandler<Boolean> {

	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
		if (ps != null) {
			ps.setInt(i, parameter ? 1 : 0);
		}
	}

	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		if (rs != null) {
			return rs.getInt(columnName) == 1;
		}
		return false;
	}

	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		if (rs != null) {
			return rs.getInt(columnIndex) == 1;
		}
		return false;
	}

	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		if (cs != null) {
			return cs.getInt(columnIndex) == 1;
		}
		return false;
	}

}
