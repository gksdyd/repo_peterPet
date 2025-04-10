package com.peterpet.demo.module.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.BaseController;
import com.peterpet.demo.module.pet.PetDto;
import com.peterpet.demo.module.pet.PetService;
import com.peterpet.demo.module.pet.PetVo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/peter/member")
public class MemberPeterController extends BaseController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	PetService petService;
	
	@RequestMapping(value = "/LoginPeterForm")
	public String loginPeterForm(MemberDto memberDto, Model model) {
		return "peter/member/LoginPeterForm";
	}
	
	@RequestMapping(value = "/SignupPeterForm")
	public String singUpPeterForm() {
		return "peter/member/SignupPeterForm";
	}
	
	@RequestMapping(value = "/UpdatePeterForm")
	public String UpdatePeterForm(MemberDto memberDto) {
		memberService.update(memberDto);
		return "redirect:/peter/member/MyAccountPeterForm";
	}
	
	@RequestMapping(value = "/InsertPeterForm")
	public String InsertPeterForm(MemberDto memberDto) {
		memberDto.setUserPassword(encodeBcrypt(memberDto.getUserPassword(), 10));
		memberService.insert(memberDto);
		return "redirect:/peter/index/IndexPeterView";
	}
	
	@RequestMapping(value = "/MyAccountPeterForm")
	public String MyAccountPeterForm(MemberDto memberDto, PetVo petVo,
			Model model, HttpSession httpSession) {
		List<PetDto> pets = new ArrayList<>();
		
		memberDto.setUserSeq((String)httpSession.getAttribute("sessSeqXdm"));
		petVo.setUserSeq((String)httpSession.getAttribute("sessSeqXdm"));
		
		pets = petService.selectListPeterPets(petVo);
		petVo.calculateAge(pets);
		
		model.addAttribute("item", memberService.selectOne(memberDto));
		model.addAttribute("list", pets);
		return "peter/member/MyAccountPeterForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/CheckIdPeterProc")
	public Map<String, Object> checkIdPeterProc(MemberDto dto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (memberService.checkId(dto) == 0) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/CheckEmailPeterProc")
	public Map<String, Object> checkEmailPeterProc(MemberDto dto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if (memberService.checkEmail(dto) == 0) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/PasswordChangePeterProc")
	public Map<String, Object> passwordChangePeterProc(MemberDto dto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		MemberDto rtMember = memberService.selectOne(dto);
		
		if (matchesBcrypt(dto.getUserPassword(), rtMember.getUserPassword(), 10)) {
			dto.setUserPassword(encodeBcrypt(dto.getNewPassword(), 10));
			memberService.updatePassword(dto);
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/PhoneXdmProc")
	public Map<String, Object> phoneXdmProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();		
		return returnMap;
	}
}
