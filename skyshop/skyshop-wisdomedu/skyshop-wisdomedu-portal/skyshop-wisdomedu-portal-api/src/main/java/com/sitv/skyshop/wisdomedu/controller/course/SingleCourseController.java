/**
 *
 */
package com.sitv.skyshop.wisdomedu.controller.course;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.wisdomedu.dto.course.SingleCourseInfo;
import com.sitv.skyshop.wisdomedu.service.course.ISingleCourseService;

import io.swagger.annotations.Api;

/**
 * @author zfj20
 */
@Api("课程话题接口")
@Validated
@RestController
@RequestMapping("/singlecourse")
public class SingleCourseController extends BaseRestController<ISingleCourseService, SingleCourseInfo> {

}
