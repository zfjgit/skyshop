package com.sitv.skyshop.common.interceptor.auth;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sitv.skyshop.common.dto.UserSessionInfo;
import com.sitv.skyshop.common.interceptor.auth.annotation.AuthorizationRequired;
import com.sitv.skyshop.common.utils.Constants;
import com.sitv.skyshop.common.utils.UserSessionContext;
import com.sitv.skyshop.dto.ResponseInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private Environment env;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");

		String corsOrigin = env.getProperty(Constants.ACCESS_CONTROL_ALLOW_ORIGIN);
		if (CorsUtils.isCorsRequest(request)) {
			// log.debug("CORS REQUEST>>>");
			response.addHeader("Access-Control-Allow-Origin", corsOrigin);
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
			response.addHeader("Access-Control-Allow-Headers", getAllowHeaders());
			response.addHeader("Access-Control-Max-Age", "3600");
		}

		boolean authInterceptorEnabled = Boolean.valueOf(env.getProperty(Constants.AUTH_INTERCEPTOR_ENABLED));
		if (!authInterceptorEnabled) {
			return true;
		}
		log.debug("TOKEN-HEADER CHECK>>>>");
		log.debug("refer=" + request.getRemoteAddr());

		String url = request.getRequestURL().toString();

		log.debug("url=" + url);

		if (url.endsWith("error") || url.endsWith(".html") || url.endsWith(".css")) {
			return true;
		}

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		log.debug("handler=" + handler.getClass().getName());

		if (Constants.SPRING_PROFILES_DEV.equals(env.getProperty("spring.profiles.active"))) {
			log.debug("开发模式，跳过检查>>>>");
			return true;
		}

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
		UserSessionInfo loginUserInfo = (UserSessionInfo) request.getSession().getAttribute(Constants.USER_KEY);
		log.debug("loginUserInfo=" + loginUserInfo);
		if (loginUserInfo == null || loginUserInfo.getToken() == null) {
			log.debug("SC_UNAUTHORIZED----->登录信息为空");
			response.getWriter().print(ResponseInfo.UNAUTHORIZED_ERROR("登录信息为空"));
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		if (System.currentTimeMillis() - loginUserInfo.getLastAccessTime() > Long.valueOf(env.getProperty(Constants.TOKEN_LIFE_LONG)) * 60 * 1000) {
			log.debug("SC_UNAUTHORIZED----->TOKEN超时");
			response.getWriter().print(ResponseInfo.UNAUTHORIZED_ERROR("TOKEN超时"));
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		String token = request.getHeader(Constants.TOKEN_HEADER);
		if (token == null || !loginUserInfo.getToken().equals(token)) {
			log.debug("SC_UNAUTHORIZED----->TOKEN不正确");
			response.getWriter().print(ResponseInfo.UNAUTHORIZED_ERROR("TOKEN不正确"));
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		log.debug("OK!!!");
		UserSessionContext.setSession(loginUserInfo);
		return true;
	}

	private String getAllowHeaders() {
		String headerStr = "Access-Control-Max-Age,Accept,Accept-Encoding,Accept-Language,Access-Control-Allow-Origin,Access-Control-Allow-Credentials,Access-Control-Allow-Method,Access-Control-Allow-Methods,Access-Control-Request-Headers,Authorization,Connection,Content-Type,Host,Origin,Referer,Token-Id,User-Agent";

		Set<String> headers = new HashSet<>();
		headers.add(Constants.X_REQUESTED_WITH);
		headers.add(Constants.TOKEN_HEADER);
		headers.add(Constants.HTTP_METHOD_OVERRIDE_HEADER);

		for (String h : headers) {
			headerStr += "," + h;
		}

		return headerStr;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// log.debug("TOKEN验证完成>>>");
		UserSessionContext.setSession(null);
		super.postHandle(request, response, handler, modelAndView);
	}
}
