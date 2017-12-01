/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.admin.controller.userecord;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitv.skyshop.controller.BaseRestController;
import com.sitv.skyshop.massagechair.dto.record.UserOperateRecordInfo;
import com.sitv.skyshop.massagechair.service.userecord.IOperateRecordService;

import io.swagger.annotations.Api;

/**
 * @author zfj20 @ 2017年12月8日
 */
@Api("后台操作记录接口")
@Validated
@RestController
@RequestMapping("/operaterecord")
public class OperateRecordController extends BaseRestController<IOperateRecordService, UserOperateRecordInfo> {

}
