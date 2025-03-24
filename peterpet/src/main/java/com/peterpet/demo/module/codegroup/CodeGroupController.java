package com.peterpet.demo.module.codegroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CodeGroupController {

	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmList")
	public String codeGroupXdmList(@ModelAttribute("vo") CodeGroupVo vo, Model model) {
//		setSearch(vo);
		vo.setParamsPaging(codeGroupService.selectOneCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", codeGroupService.selectList(vo));
		}		
		return "xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmForm")
	public String codeGroupXdmForm(Model model, CodeGroupDto codeGroupDto) {
		if (codeGroupService.selectMaxSeq() == null)
		{
			codeGroupDto.setCogrSeq("1");
		} else {
			codeGroupDto.setCogrSeq((codeGroupService.selectMaxSeq() + 1) + "");
		}
		model.addAttribute("item", codeGroupDto);
		return "xdm/codegroup/CodeGroupXdmForm";
	}
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmView")
	public String codeGroupXdmView(Model model, CodeGroupDto codeGroupDto) {
		model.addAttribute("item", codeGroupService.selectOne(codeGroupDto));
		return "xdm/codegroup/CodeGroupXdmForm";
	}
}
