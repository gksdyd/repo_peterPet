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
	public String codeGroupXdmForm(Model model, CodeGroupDto codeGroupDto, @ModelAttribute("vo") CodeGroupVo vo) {
		if (vo.getRegisterOrModifyFlag() == 1) {
			if (codeGroupService.selectMaxSeq() == null)
			{
				codeGroupDto.setCogrSeq("1");
			} else {
				codeGroupDto.setCogrSeq((codeGroupService.selectMaxSeq() + 1) + "");
			}
			model.addAttribute("item", codeGroupDto);
		} else {
			System.out.println("fdsfas");
			model.addAttribute("item", codeGroupService.selectOne(codeGroupDto));
		}
		return "xdm/codegroup/CodeGroupXdmForm";
	}
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmUpdt")
	public String codeGroupXdmUpdt(CodeGroupDto codeGroupDto) {
		codeGroupService.update(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmUelt")
	public String codeGroupXdmUelt(CodeGroupDto codeGroupDto) {
		codeGroupService.uelete(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/xdm/codegroup/CodeGroupXdmDelt")
	public String codeGroupXdmDelt(CodeGroupDto codeGroupDto) {
		codeGroupService.delete(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
}
