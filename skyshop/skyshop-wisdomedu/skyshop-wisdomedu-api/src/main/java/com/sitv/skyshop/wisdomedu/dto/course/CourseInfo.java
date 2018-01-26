/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.course;

import java.util.Calendar;

import com.sitv.skyshop.dto.info.FullInfoDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class CourseInfo extends FullInfoDto {

	/**
	 *
	 */
	public CourseInfo() {
	}

	public CourseInfo(Long id, Calendar createTime) {
		super(id, createTime, null);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -6941869785567059049L;

}
