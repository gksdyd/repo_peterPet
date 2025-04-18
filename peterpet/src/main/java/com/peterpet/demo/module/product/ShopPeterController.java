package com.peterpet.demo.module.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.BaseController;

@Controller
@RequestMapping(value = "/peter/shop")
public class ShopPeterController extends BaseController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/ShopPeterList")
	public String shopPeterList(@ModelAttribute("vo") ProductVo vo, Model model) {
		vo.setRowNumToShow(9);
		vo.setParamsPaging(productService.selectOneCount(vo));
		List<ProductDto> dtos = productService.selectFeedList(vo);
		
		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).calculatePrice();
		}
		model.addAttribute("list", dtos);
		return "peter/shop/ShopPeterList";
	}
	
	@RequestMapping(value = "/ShopPeterView")
	public String shopPeterView(@ModelAttribute("vo") ProductVo vo, Model model) throws Exception {
		ProductDto dto = productService.selectOnePoduct(vo);
		dto.setWeightArray(dto.getWeightArr().split(","));
		model.addAttribute("item", dto);
		deliveryTimeCheck(vo);
		return "peter/shop/ShopPeterView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ShopPeterTimeProc")
	public Map<String, Object> shopPeterTimeProc(@ModelAttribute("vo") ProductVo vo) throws Exception {
		Map<String, Object> rtMap = new HashMap<>();
		deliveryTimeCheck(vo);
		rtMap.put("day", vo.getDiffDay());
		rtMap.put("hour", vo.getDiffHour());
		rtMap.put("minute", vo.getDiffMinute());
		rtMap.put("second", vo.getDiffSecond());
		rtMap.put("delivery", vo.getDeliveryTime());
		return rtMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ShopPeterInfoProc")
	public Map<String, Object> shopPeterInfoProc(ProductVo vo) {
		Map<String, Object> rtMap = new HashMap<>();
		vo.setRowNumToShow(9);
		rtMap.put("dtos", productService.selectFeedList(vo));
		return rtMap;
	}
}
