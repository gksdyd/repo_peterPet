package com.peterpet.demo.peter.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopPeterController {

	@RequestMapping(value = "/peter/shop/shop")
	public String shopPeterList() {
		return "peter/shop/shop";
	}
}
