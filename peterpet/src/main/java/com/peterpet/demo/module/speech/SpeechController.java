package com.peterpet.demo.module.speech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/speech/peter")
public class SpeechController {

	@Autowired
	SpeechService speechService;
	
	@RequestMapping(value = "/SpeechPeterForm")
	public String speechPeterForm() {
		return "peter/speech/speech";
	}
	
	@ResponseBody
	@RequestMapping(value = "/SpeechPeterInsert")
	public String speechPeterInsert(SpeechDto dto) throws Exception {
		speechService.SpeechPeterInsert(dto);
		return dto.getPath();
	}
}
