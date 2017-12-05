/**
 *
 */
package com.sitv.skyshop.massagechair.dao.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.sitv.skyshop.common.domain.Withraw.WithrawStatus;
import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.domain.DomainObject.DeleteStatus;
import com.sitv.skyshop.massagechair.domain.agency.Agency.AgencyLevel;
import com.sitv.skyshop.massagechair.domain.device.GSMModule.GSMModuleStatus;
import com.sitv.skyshop.massagechair.domain.device.MassageChair.ChairStatus;
import com.sitv.skyshop.massagechair.domain.device.SIMCard.SIMCardOperator;
import com.sitv.skyshop.massagechair.domain.device.SIMCard.SIMCardStatus;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionStatus;
import com.sitv.skyshop.massagechair.domain.device.malfunction.Malfunction.MalfunctionType;
import com.sitv.skyshop.massagechair.domain.order.Order.PayStatus;
import com.sitv.skyshop.massagechair.domain.order.Order.PayType;
import com.sitv.skyshop.massagechair.domain.record.UseRecord.UseRecordType;
import com.sitv.skyshop.massagechair.domain.record.UserOperateRecord.OperateType;
import com.sitv.skyshop.massagechair.domain.user.User.UserStatus;
import com.sitv.skyshop.massagechair.domain.user.User.UserType;

/**
 * @author zfj20 @ 2017年11月16日
 */
@MappedTypes({ AgencyLevel.class, MalfunctionStatus.class, MalfunctionType.class, PayStatus.class, PayType.class, SIMCardOperator.class, SIMCardStatus.class, ChairStatus.class,
                GSMModuleStatus.class, UserType.class, UserStatus.class, WithrawStatus.class, DeleteStatus.class, OperateType.class, UseRecordType.class })
public class EnumTypeHandler<E extends BaseEnum<?, String>> extends BaseTypeHandler<E> {

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
		ps.setString(i, e.getCode());
	}

	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String code = rs.getString(columnName);
		if (rs.wasNull()) {
			return null;
		}
		return valueOf(code);
	}

	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String code = rs.getString(columnIndex);
		if (rs.wasNull()) {
			return null;
		}
		return valueOf(code);
	}

	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String code = cs.getString(columnIndex);
		if (cs.wasNull()) {
			return null;
		}
		return valueOf(code);
	}

	private E valueOf(String code) {
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
