package com.peterpet.demo.peter.member.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPeterController {

	@RequestMapping(value = "/peter/member/login")
	public String loginPeterForm() {
		return "peter/member/login";
	}
}
