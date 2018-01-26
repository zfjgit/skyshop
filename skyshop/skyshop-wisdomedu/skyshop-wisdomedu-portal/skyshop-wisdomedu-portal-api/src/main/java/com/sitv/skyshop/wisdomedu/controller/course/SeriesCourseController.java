/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.course;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.wisdomedu.dto.course.SeriesCourseInfo;
import com.sitv.skyshop.wisdomedu.service.course.ISeriesCourseService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("系列课接口")
@Validated
@RestController
@RequestMapping("/seriescourse")
public class SeriesCourseController extends BaseRestController<ISeriesCourseService, SeriesCourseInfo> {

}
