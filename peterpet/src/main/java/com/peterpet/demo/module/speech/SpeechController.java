package com.peterpet.demo.module.speech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.base.BaseController;
import com.peterpet.demo.module.util.UtilWav;

@Controller
@RequestMapping(value = "/speech/peter")
public class SpeechController extends BaseController {

	@Autowired
	SpeechService speechService;
	
	@RequestMapping(value = "/SpeechPeterForm")
	public String speechPeterForm() {
		return "peter/speech/Speech";
	}
	
	@ResponseBody
	@RequestMapping(value = "/SpeechPeterInsert")
	public String speechPeterInsert(SpeechDto dto) throws Exception {
		speechService.SpeechPeterInsert(dto);
		return speechToTextResponse(UtilWav.getWavData(UtilWav.multipartFileToFile(dto.getUploadImg1()[0])));
	}
}
