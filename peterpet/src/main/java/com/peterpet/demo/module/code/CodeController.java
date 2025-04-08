package com.peterpet.demo.module.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.BaseController;

@Controller
@RequestMapping(value = "/xdm/code")
public class CodeController extends BaseController {

	@Autowired
	CodeService codeService;
	
	@RequestMapping(value = "/CodeXdmList")
	public String codeXdmList(@ModelAttribute("vo") CodeVo vo, Model model) {
		initSearchTime(vo);
		vo.setParamsPaging(codeService.selectOneCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", codeService.selectList(vo));
		}		
		return "xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value = "/CodeXdmForm")
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
	
	@RequestMapping(value = "/CodeXdmInst")
	public String codeXdmInst(CodeDto codeDto) {
		codeService.insert(codeDto);
		return "redirect:/xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value = "/CodeXdmUpdt")
	public String codeXdmUpdt(CodeDto codeDto) {
		codeService.update(codeDto);
		return "redirect:/xdm/code/CodeXdmList";
	}
	
	@RequestMapping(value = "/CodeXdmUelt")
	public String codeGroupXdmUelt(CodeDto codeDto) {
		codeService.uelete(codeDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/CodeXdmDelt")
	public String codeGroupXdmDelt(CodeDto codeDto) {
		codeService.delete(codeDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/CodeXdmDeltProc")
	public void codeXdmDeltProc(CodeVo vo) {
		codeService.severalDelete(vo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/CodeXdmUeltProc")
	public void codeXdmUeltProc(CodeVo vo) {
		codeService.severalUelete(vo);
	}
}
