package com.peterpet.demo.peter.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.Constants;
import com.peterpet.demo.module.member.MemberDto;
import com.peterpet.demo.module.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/peter/member")
public class MemberPeterController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/LoginPeterForm")
	public String loginPeterForm() {
		return "peter/member/LoginPeterForm";
	}
	
	@RequestMapping(value = "/SignupPeterForm")
	public String singUpPeterForm() {
		return "peter/member/SignupPeterForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/PhoneXdmProc")
	public Map<String, Object> phoneXdmProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();		
		return returnMap;
	}
}
