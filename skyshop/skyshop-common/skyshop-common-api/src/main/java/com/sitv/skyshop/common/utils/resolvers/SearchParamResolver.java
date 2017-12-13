/**
 *
 */
package com.sitv.skyshop.common.utils.resolvers;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.sitv.skyshop.common.utils.resolvers.annotation.SearchParamType;
import com.sitv.skyshop.dto.SearchParamInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zfj20 @ 2017年12月11日
 */
@Slf4j
public class SearchParamResolver implements HandlerMethodArgumentResolver {

	public boolean supportsParameter(MethodParameter parameter) {
		log.debug("解析搜索条件对象>>>>");
		log.debug("type=" + parameter.getParameterType().getName());
		log.debug("annotations=" + parameter.getParameterAnnotations());
		return parameter.getParameterType() == SearchParamInfo.class && parameter.getParameterAnnotation(SearchParamType.class) != null;
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String paramName = parameter.getParameterName();
		log.debug("parameter name=" + paramName);
		String paramValue = webRequest.getParameter(paramName);
		log.debug("value=" + paramValue);

		Iterator<String> names = webRequest.getParameterNames();
		while (names.hasNext()) {
			log.debug("name=" + names.next());
		}

		log.debug("handled?=" + mavContainer.isRequestHandled());

		Iterator<String> keyIterator = mavContainer.getModel().keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			log.debug("key=" + key);
		}

		Object attribute = null;
		if (mavContainer.containsAttribute(paramName)) {
			attribute = mavContainer.getModel().get(paramName);
			log.debug("attr=" + attribute);
		} else {
			attribute = BeanUtils.instantiateClass(parameter.getParameterType());
		}

		ServletInputStream is = webRequest.getNativeRequest(HttpServletRequest.class).getInputStream();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		os.close();
		is.close();
		String params = os.toString("UTF-8");
		log.debug("params=" + params);

		Class<?> dataType = parameter.getParameterAnnotation(SearchParamType.class).value();
		log.debug("dataType=" + dataType.getName());
		return new SearchParamInfo<>();
	}

}
