/**
 *
 */
package com.sitv.skyshop.common.utils.converters;

import java.util.Calendar;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;

/**
 * @author zfj20 @ 2017年12月9日
 */
public class CalendarConverter implements Converter<String, Calendar> {

	public Calendar convert(String source) {
		if (!StringUtils.isEmpty(source)) {
			try {
				source = source.trim();
				if (source.contains("-")) {
					if (source.contains(":")) {
						return Utils.toCalendar(source);
					} else {
						return Utils.toCalendar(source, Constants.DATE_FORMAT_1);
					}
				} else if (source.contains("/")) {
					if (source.contains(":")) {
						return Utils.toCalendar(source, Constants.DATETIME_FORMAT_2);
					} else {
						return Utils.toCalendar(source, Constants.DATE_FORMAT_2);
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(String.format("解析日期 %s 出错", source));
			}
		}

		throw new RuntimeException(String.format("解析日期 %s 出错", source));
	}

}
