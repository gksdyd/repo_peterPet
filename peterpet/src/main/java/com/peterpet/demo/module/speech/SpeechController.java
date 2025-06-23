package com.peterpet.demo.module.speech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.peterpet.demo.module.base.BaseController;

import jakarta.servlet.http.HttpServletRequest;

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
	public String speechPeterInsert(SpeechDto dto, HttpServletRequest request) throws Exception {		
		String url = request.getScheme() + "://" + request.getServerName() + ":8000/speechApi/";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(dto.getUploadImg1()[0].getBytes()) {
            @Override
            public String getFilename() {
                return dto.getUploadImg1()[0].getOriginalFilename();
            }
        });
        
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        
        System.out.println("Response from FastAPI: " + response.getBody());
        
		return response.getBody();
	}
}
