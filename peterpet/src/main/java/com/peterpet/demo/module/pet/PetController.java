package com.peterpet.demo.module.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/xdm/pet")
public class PetController {

	@Autowired
	PetService petService;
	
	@RequestMapping(value = "/PetXdmList")
	public String petXdmList(Model model, @ModelAttribute("vo") PetVo vo) {
		vo.setParamsPaging(petService.selectOneCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", petService.selectList(vo));
		}
		return "xdm/pet/PetXdmList";
	}
	
	@RequestMapping(value = "/PetXdmListSelectUser")
	public String petXdmListSelectUser(Model model, PetVo vo) {
		vo.setParamsPaging(petService.selectOneCountOneUser(vo));
		model.addAttribute("list", petService.selectListOneUser(vo));
		model.addAttribute("vo", vo);
		return "xdm/pet/PetXdmList";
	}
	
	@RequestMapping(value = "/PetXdmView")
	public String petXdmView(Model model, PetVo vo) {
		model.addAttribute("item", petService.selectOne(vo));
		model.addAttribute("listPersonal", petService.selectOnePersonal(vo));
		model.addAttribute("listDisease", petService.selectOneDisease(vo));
		return "xdm/pet/PetXdmView";
	}
}
