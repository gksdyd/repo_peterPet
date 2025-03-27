package com.peterpet.demo.peter.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.Constants;
import com.peterpet.demo.module.member.MemberDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/peter/member")
public class MemberPeterController {
	
	@RequestMapping(value = "/LoginPeterForm")
	public String loginPeterForm() {
		return "peter/member/LoginPeterForm";
	}
	
	@RequestMapping(value = "/SignupPeterForm")
	public String singUpPeterForm() {
		return "peter/member/SignupPeterForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/LoginPeterProc")
	public Map<String, Object> loginXdmProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
//		MemberDto rtMember = memberService.selectOneLogin(dto);
		
//		if (rtMember != null) {
//			httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
//			httpSession.setAttribute("sessSeqXdm", rtMember.getUserSeq());
//			httpSession.setAttribute("sessIdXdm", rtMember.getUserId());
//			httpSession.setAttribute("sessNameXdm", rtMember.getUserName());
//			
//			returnMap.put("rt", "success");
//		} else {
//			returnMap.put("rt", "fali");
//		}
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/LogoutPeterProc")
	public Map<String, Object> logoutXdmProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("rt", "success");
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/PhoneXdmProc")
	public Map<String, Object> phoneXdmProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();		
		return returnMap;
	}
}
