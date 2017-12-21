/**
 *
 */
package com.sitv.skyshop.common.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.Utils;

/**
 * @author zfj20 @ 2018年1月12日
 */
@WebFilter(filterName = "HttpMethodOverrideFilter", urlPatterns = "/*")
public class HttpMethodOverrideFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String overrideMethod = httpServletRequest.getHeader(Constants.HTTP_METHOD_OVERRIDE_HEADER);
		if ("POST".equalsIgnoreCase(httpServletRequest.getMethod()) && !Utils.isNull(overrideMethod) && !"POST".equalsIgnoreCase(overrideMethod)) {
			chain.doFilter(new HttpMethodOverrideRequest(httpServletRequest), response);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

	private class HttpMethodOverrideRequest extends HttpServletRequestWrapper {

		public HttpMethodOverrideRequest(javax.servlet.http.HttpServletRequest request) {
			super(request);
		}

		public String getMethod() {
			return getHeader(Constants.HTTP_METHOD_OVERRIDE_HEADER);
		}
	}
}
