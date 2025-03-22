package com.peterpet.demo.module.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeGroupController {

	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmList")
	public String codeGroupXdmList(CodeGroupVo vo, Model model) {
		vo.setParamsPaging(codeGroupService.selectOneCount());
		model.addAttribute("list", codeGroupService.selectList(vo));
		model.addAttribute("vo", vo);		
		return "xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmForm")
	public String codeGroupXdmForm(Model model, CodeGroupDto codeGroupDto) {
		if (codeGroupService.selectMaxSeq() == null)
		{
			codeGroupDto.setCogrSeq("0");
		} else {
			codeGroupDto.setCogrSeq(codeGroupService.selectMaxSeq());						
		}
		model.addAttribute("item", codeGroupDto);
		return "xdm/codegroup/CodeGroupXdmForm";
	}
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
}
