package com.peterpet.demo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.peterpet.demo.common.interceptor.CheckLoginSessionInterceptor;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckLoginSessionInterceptor())
//		.order(1)
		.addPathPatterns("/*/*/*Xdm*", "/*/*/*/*Usr*")
		.excludePathPatterns(
//				"/resources/**",
				"/static/**",
				"/xdm/member/LoginXdmForm",
				"/xdm/member/LoginXdmProc"
		);
	}

	
}
