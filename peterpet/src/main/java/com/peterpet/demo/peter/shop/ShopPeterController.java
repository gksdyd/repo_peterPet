package com.peterpet.demo.peter.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/peter/shop")
public class ShopPeterController {

	@RequestMapping(value = "/ShopPeterList")
	public String shopPeterList() {
		return "peter/shop/ShopPeterList";
	}
}
