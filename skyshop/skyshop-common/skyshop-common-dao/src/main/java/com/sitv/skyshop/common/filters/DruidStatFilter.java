/**
 *
 */
package com.sitv.skyshop.common.filters;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @author zfj20
 * @version 2017年7月28日
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = { @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico, /druid/*")// 忽略资源
})
public class DruidStatFilter extends WebStatFilter {

}
