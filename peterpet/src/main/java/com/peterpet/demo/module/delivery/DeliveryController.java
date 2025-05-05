package com.peterpet.demo.module.delivery;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/peter/delivery")
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;
	
	@RequestMapping(value = "/DeliveryPeterProc")
	public String deliveryPeterProc(DeliveryDto dto, Model model) {
		if (dto.getDeliMain() == 1) {
			deliveryService.mainUpdate(dto);
		}
		deliveryService.insert(dto);
		
		model.addAttribute("deliveries", deliveryService.selectList(dto));
		
		return "/peter/include/delivery :: delivery";
	}
	
	@ResponseBody
	@RequestMapping(value = "/DeliveryPeterModifyProc")
	public Map<String, Object> deliveryPeterModifyProc(DeliveryVo vo) {
		Map<String, Object> rtMap = new HashMap<>();
		rtMap.put("dto", deliveryService.selectOne(vo));
		return rtMap;
	}
}
