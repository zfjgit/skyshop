/**
 *
 */
package com.sitv.skyshop.wisdomedu.dto.course;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sitv.skyshop.dto.info.FullInfoDto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zfj20
 */
@Getter
@Setter
public class CourseAdvisoryInfo extends FullInfoDto {

	private static final long serialVersionUID = -2053299389148408810L;
	private String title;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Calendar lastAdvisoryTime;
	private int advisoryCount;
}
