/**
 *
 */
package com.sitv.skyshop.common.utils.resolvers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月13日
 */
@Slf4j
@Component
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		log.error("发生错误", ex);

		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.addStaticAttribute("code", 500);
		jsonView.addStaticAttribute("message", ex.getMessage());
		ModelAndView mav = new ModelAndView();
		mav.setView(jsonView);
		return mav;
	}

}
