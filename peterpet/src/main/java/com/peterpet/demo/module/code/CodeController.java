package com.peterpet.demo.module.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peterpet.demo.module.codegroup.CodeGroupVo;

@Controller
public class CodeController {

	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/xdm/code/CodeXdmList")
	public String codeXdmList(@ModelAttribute("vo") CodeVo vo, Model model) {
		vo.setParamsPaging(codeService.selectOneCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", codeService.selectList(vo));
		}		
		return "xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value = "/xdm/code/CodeXdmForm")
	public String codeXdmForm(Model model, CodeDto codeDto, @ModelAttribute("vo") CodeVo vo) {
		if (vo.getRegisterOrModifyFlag() == 1) {
			if (codeService.selectMaxSeq() == null)
			{
				codeDto.setCodeSeq("1");
			} else {
				codeDto.setCodeSeq((codeService.selectMaxSeq() + 1) + "");
			}
			model.addAttribute("item", codeDto);
			model.addAttribute("list", codeService.selectCodeGroupName());
		} else {
			model.addAttribute("item", codeService.selectOne(codeDto));
			model.addAttribute("list", codeService.selectCodeGroupName());
		}
		return "xdm/code/CodeXdmForm";
	}
	
	@RequestMapping(value = "/xdm/code/CodeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:/xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value = "/xdm/code/CodeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		codeService.update(codeDto);
		return "redirect:/xdm/code/CodeXdmList";
	}
}
