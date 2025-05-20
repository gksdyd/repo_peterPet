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
		.addPathPatterns("/*/*/*Xdm*", "/*/*/*Peter*")
		.excludePathPatterns(
//				"/resources/**",
				"/static/**",
				"/xdm/member/LoginXdmForm",
				"/xdm/member/LoginXdmProc",
				"/xdm/product/ProductXdmFilterProc",
				"/xdm/product/ProductXdmFormProc",
				"/xdm/product/ProductXdmSupplyProc",
				"/xdm/product/ProductXdmSupplyFormProc",
				"/peter/member/LoginPeterForm",
				"/peter/member/SignupPeterForm",
				"/peter/index/IndexPeterView",
				"/peter/shop/ShopPeterList",
				"/peter/member/InsertPeterForm",
				"/peter/member/CheckIdPeterProc",
				"/peter/member/CheckEmailPeterProc",
				"/peter/member/LoginPeterProc",
				"/peter/member/CheckPhonePeterProc",
				"/peter/member/ReviewPeterInst",
				"/peter/shop/ShopPeterView",
				"/peter/shop/ShopPeterTimeProc",
				"/peter/shop/ShopPeterInfoProc",
				"/peter/shop/ReviewPeterProc",
				"/peter/shop/ShopPeterSearch",
				"/peter/shop/ShopPeterFilter",
				"/peter/shop/ShopPeterSupplyProc",
				"/peter/wishlist/WishlistPeterProc"
		);
	}

	
}
