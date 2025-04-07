package com.peterpet.demo.module.codegroup;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.BaseController;

@Controller
@RequestMapping(value = "/xdm/codegroup")
public class CodeGroupController extends BaseController {

	@Autowired
	CodeGroupService codeGroupService;
	
	@RequestMapping(value = "/CodeGroupXdmList")
	public String codeGroupXdmList(@ModelAttribute("vo") CodeGroupVo vo, Model model) {
		initSearchTime(vo);
		vo.setParamsPaging(codeGroupService.selectOneCount(vo));
		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", codeGroupService.selectList(vo));
		}		
		return "xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/CodeGroupXdmForm")
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
			model.addAttribute("item", codeGroupService.selectOne(codeGroupDto));
		}
		return "xdm/codegroup/CodeGroupXdmForm";
	}
	
	@RequestMapping(value = "/CodeGroupXdmInst")
	public String codeGroupXdmInst(CodeGroupDto codeGroupDto) {
		codeGroupService.insert(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/CodeGroupXdmUpdt")
	public String codeGroupXdmUpdt(CodeGroupDto codeGroupDto) {
		codeGroupService.update(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/CodeGroupXdmUelt")
	public String codeGroupXdmUelt(CodeGroupDto codeGroupDto) {
		codeGroupService.uelete(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@RequestMapping(value = "/CodeGroupXdmDelt")
	public String codeGroupXdmDelt(CodeGroupDto codeGroupDto) {
		codeGroupService.delete(codeGroupDto);
		return "redirect:/xdm/codegroup/CodeGroupXdmList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/CodeGroupXdmDeltProc")
	public void codeGroupXdmDeltProc(CodeGroupVo vo) {
		codeGroupService.severalDelete(vo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/CodeGroupXdmUeltProc")
	public void codeGroupXdmUeltProc(CodeGroupVo vo) {
		codeGroupService.severalUelete(vo);
	}
}
