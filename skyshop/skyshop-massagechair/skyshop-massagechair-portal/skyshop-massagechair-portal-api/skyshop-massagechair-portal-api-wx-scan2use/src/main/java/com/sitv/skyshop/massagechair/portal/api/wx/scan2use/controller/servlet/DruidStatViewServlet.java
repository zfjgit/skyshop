/**
 *
 */
package com.sitv.skyshop.massagechair.portal.api.wx.scan2use.controller.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * @author zfj20
 * @version 2017年7月28日
 */
@WebServlet(urlPatterns = "/druid/*", initParams = { @WebInitParam(name = "loginUsername", value = "druid"), // 用户名
                @WebInitParam(name = "loginPassword", value = "druid"), // 密码
                @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset
})
public class DruidStatViewServlet extends StatViewServlet {

	private static final long serialVersionUID = -2143459794120909334L;

}
