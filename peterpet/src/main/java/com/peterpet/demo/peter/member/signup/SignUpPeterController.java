package com.peterpet.demo.peter.member.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpPeterController {

	@RequestMapping(value = "/peter/member/signUp")
	public String singUpPeterForm() {
		return "peter/member/signUp";
	}
}
