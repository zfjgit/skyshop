/**
 * 
 */
package com.sitv.skyshop.sync.receiverdata.druid;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 
 * @author zfj20 @ 2017年9月15日
 */
@WebFilter(filterName="druidStatFilter",urlPatterns="/*",
initParams={
        @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})
public class DruidStatFilter extends WebStatFilter {

}
