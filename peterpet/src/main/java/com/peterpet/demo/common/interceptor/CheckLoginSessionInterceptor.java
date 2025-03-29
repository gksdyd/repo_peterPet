package com.peterpet.demo.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.peterpet.demo.common.common.Constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLoginSessionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		관리자용
		if(request.getRequestURI().contains(Constants.ADMIN_URI_CONTAINS_TEXT)) {
			if (request.getSession().getAttribute(Constants.SESSION_ATTR_SEQ) != null) {
				// by pass
			} else {
				response.sendRedirect(Constants.SEND_REDIRECT_ADDRESS_XDM);
		        return false;
			}
		} else {
//			by pass
		}
		
//		사용자용
		if(request.getRequestURI().contains(Constants.PETER_URI_CONTAINS_TEXT)) {
			if (request.getSession().getAttribute(Constants.SESSION_ATTR_SEQ) != null) {
				// by pass
			} else {
				response.sendRedirect(Constants.SEND_REDIRECT_ADDRESS_PETER);
				return false;
			}
		} else {
//			by pass
		}		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}	
}
