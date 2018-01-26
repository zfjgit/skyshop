/**
 *
 */
package com.sitv.skyshop.wisdomedu.dao.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.sitv.skyshop.domain.BaseEnum;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse.CourseForm;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse.JoinSeriesCourseStatus;
import com.sitv.skyshop.wisdomedu.domain.course.SingleCourse.LiveType;
import com.sitv.skyshop.wisdomedu.domain.course.CourseChatRecord.CourseChatRecordType;
import com.sitv.skyshop.wisdomedu.domain.course.CourseResource.CourseResourceType;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse.ChargeType;
import com.sitv.skyshop.wisdomedu.domain.course.SeriesCourse.PromotionType;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioIncomes.IncomeStatus;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioIncomes.IncomeType;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioPageSettings.LinkType;
import com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioWithrawRecord.WithrawStatus;
import com.sitv.skyshop.wisdomedu.domain.user.UserCourseAdvisory.AdvisorySubjectType;
import com.sitv.skyshop.wisdomedu.domain.user.UserOrder.OrderStatus;
import com.sitv.skyshop.wisdomedu.domain.user.UserOrder.PayType;
import com.sitv.skyshop.wisdomedu.domain.user.UserOrderItem.SubjectType;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年11月16日
 */
@Slf4j
@MappedTypes({ SubjectType.class, com.sitv.skyshop.wisdomedu.domain.livestudio.LiveStudioNews.SubjectType.class, ChargeType.class, CourseChatRecordType.class, CourseForm.class,
                CourseResourceType.class, IncomeStatus.class, IncomeType.class, LinkType.class, LiveType.class, OrderStatus.class, PayType.class, PromotionType.class,
                WithrawStatus.class, AdvisorySubjectType.class, JoinSeriesCourseStatus.class })
public class EnumTypeHandler<E extends BaseEnum<?, C>, C> extends BaseTypeHandler<E> {

	private Class<E> type;
	private E[] enums;

	public EnumTypeHandler(Class<E> type) {
		this.type = type;
		this.enums = type.getEnumConstants();
		log.debug("enums=" + Arrays.toString(enums));
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
		log.debug("type=" + type.getName());
		log.debug("enums=" + Arrays.toString(enums));
		for (E e : enums) {
			log.debug("e=" + e.getCode());
			log.debug("e=" + e.getClass().getName());
			log.debug("code=" + code);
			log.debug("code=" + code.getClass().getName());
			if (e.getCode().toString().equals(code.toString())) {
				return e;
			}
		}
		throw new IllegalArgumentException("未知的枚举类型：" + code + "，请核对" + type.getSimpleName());
	}
}
