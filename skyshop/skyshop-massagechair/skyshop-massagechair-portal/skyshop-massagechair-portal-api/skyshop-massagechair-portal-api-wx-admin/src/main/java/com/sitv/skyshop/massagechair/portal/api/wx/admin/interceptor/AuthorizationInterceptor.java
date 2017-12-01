package com.sitv.skyshop.massagechair.portal.api.wx.admin.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.UserSessionContext;
import com.sitv.skyshop.dto.ResponseInfo;
import com.sitv.skyshop.massagechair.dto.user.LoginUserInfo;
import com.sitv.skyshop.massagechair.portal.api.wx.admin.interceptor.annotation.AuthorizationRequired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private Environment env;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug("TOKEN_HEADER CHECK>>>>");
		log.debug("refer=" + request.getRemoteAddr());

		response.setCharacterEncoding("UTF-8");

		if (CorsUtils.isCorsRequest(request)) {
			log.debug("CORS REQUEST>>>");
			response.addHeader("Access-Control-Allow-Origin", env.getProperty("access-control-allow-origin"));
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
			response.addHeader("Access-Control-Max-Age", "3600");
		}

		String url = request.getRequestURL().toString();

		log.debug("url=" + url);

		if (url.endsWith("error") || url.endsWith(".html") || url.endsWith(".css")) {
			return true;
		}

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		log.debug("handler=" + handler.getClass().getName());

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		AuthorizationRequired authorizationRequired = method.getAnnotation(AuthorizationRequired.class);
		if (authorizationRequired != null) {
			if (authorizationRequired.value()) {
				log.debug("方法需要验证token>>>>");
				return checkToken(request, response);
			} else {
				log.debug("方法不需要验证token");
				return true;
			}
		} else {
			AuthorizationRequired classAuthorizationRequired = handler.getClass().getAnnotation(AuthorizationRequired.class);
			if (classAuthorizationRequired != null) {
				if (classAuthorizationRequired.value()) {
					log.debug("类需要验证token>>>>");
					return checkToken(request, response);
				} else {
					log.debug("类不需要验证token");
					return true;
				}
			}
		}

		return checkToken(request, response);
	}

	private boolean checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(Constants.USER_KEY);
		if (loginUserInfo == null || loginUserInfo.getToken() == null) {
			log.debug("SC_UNAUTHORIZED----->登录信息为空");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().print(ResponseInfo.UNAUTHORIZED_ERROR("登录信息为空"));
			return false;
		}
		if (System.currentTimeMillis() - loginUserInfo.getLastAccessTime() > Constants.TOKEN_LIFETIME) {
			log.debug("SC_UNAUTHORIZED----->TOKEN超时");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().print(ResponseInfo.UNAUTHORIZED_ERROR("TOKEN超时"));
			return false;
		}
		String token = request.getHeader(Constants.TOKEN_HEADER);
		if (token == null || !loginUserInfo.getToken().equals(token)) {
			log.debug("SC_UNAUTHORIZED----->TOKEN不正确");
			response.getWriter().print(ResponseInfo.UNAUTHORIZED_ERROR("TOKEN不正确"));
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		UserSessionContext.setSession(loginUserInfo);
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		UserSessionContext.setSession(null);
		super.postHandle(request, response, handler, modelAndView);
	}
}
