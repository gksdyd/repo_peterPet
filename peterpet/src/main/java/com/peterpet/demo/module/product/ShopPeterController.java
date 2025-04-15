package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/peter/shop")
public class ShopPeterController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/ShopPeterList")
	public String shopPeterList(@ModelAttribute("vo") ProductVo vo, Model model) {
		vo.setRowNumToShow(12);
		vo.setParamsPaging(productService.selectOneCount(vo));
		List<ProductDto> dtos = productService.selectFeedList(vo);
		
		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).calculatePrice();
		}
		model.addAttribute("list", dtos);
		return "peter/shop/ShopPeterList";
	}
}
