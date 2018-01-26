/**
 *
 */
package com.sitv.skyshop.tbataobao.dao.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.tbataobao.domain.CheckFailedRemark.SubjectType;
import com.sitv.skyshop.tbataobao.domain.ICheckStatus.CheckStatus;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MappedTypes({ SubjectType.class, CheckStatus.class })
public class EnumTypeHandler<E extends BaseEnum<?, C>, C> extends BaseTypeHandler<E> {

	private Class<E> type;
	private E[] enums;

	public EnumTypeHandler(Class<E> type) {
		this.type = type;
		this.enums = type.getEnumConstants();
		if (this.enums == null) {
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
		}
	}

	public void setNonNullParameter(PreparedStatement ps, int i, E e, JdbcType jdbcType) throws SQLException {
		ps.setObject(i, e.getCode());
	}

	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Object code = rs.getObject(columnName);
		if (rs.wasNull()) {
			return null;
		}
		return valueOf(code);
	}

	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Object code = rs.getObject(columnIndex);
		if (rs.wasNull()) {
			return null;
		}
		return valueOf(code);
	}

	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Object code = cs.getObject(columnIndex);
		if (cs.wasNull()) {
			return null;
		}
		return valueOf(code);
	}

	private E valueOf(Object code) {
		if (code == null || "".equals(code)) {
			return null;
		}
		for (E e : enums) {
			if (e.getCode().equals(code)) {
				return e;
			}
		}
		throw new IllegalArgumentException("未知的枚举类型：" + code + "，请核对" + type.getSimpleName());
	}
}
