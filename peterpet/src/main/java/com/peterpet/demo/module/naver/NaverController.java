package com.peterpet.demo.module.naver;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterpet.demo.module.base.Constants;
import com.peterpet.demo.module.member.MemberDto;
import com.peterpet.demo.module.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/naver/xdm")
public class NaverController {

	@Value("${clientId}")
	private String clientId;
	
	@Value("${clientSecret}")
    private String clientSecret;
	
	@Autowired
	MemberService memberService; 
    
	@ResponseBody
	@RequestMapping(value = "/NaverXdmToken")
	public Map<String, Object> naverXdmToken(@RequestBody NaverDto request, HttpSession httpSession)
			throws JsonMappingException, JsonProcessingException {
        String tokenUrl = "https://nid.naver.com/oauth2.0/token";

        // 요청 파라미터 구성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", request.getCode());
        params.add("state", request.getState());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, entity, String.class);
        
		String responseBody = response.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(responseBody);
		String accessToken = rootNode.path("access_token").asText();
		
		response = callNaverApi(accessToken);
		
		responseBody = response.getBody();
		System.out.println(responseBody);
		JsonNode responseNode = objectMapper.readTree(responseBody).path("response");
		
		MemberDto dto = new MemberDto();
		dto.setUserName(responseNode.path("name").asText());
		dto.setUserPhone(responseNode.path("mobile").asText());
		
		Map<String, Object> rtMap = new HashMap<>();
		dto = memberService.naverLogin(dto);
		if (dto != null) {
			httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
			httpSession.setAttribute("sessSeqXdm", dto.getUserSeq());
			httpSession.setAttribute("sessIdXdm", dto.getUserId());
			httpSession.setAttribute("sessNameXdm", dto.getUserName());
			
			rtMap.put("rt", "success");
		} else {
			rtMap.put("rt", "fail");
		}
		
        return rtMap;
	}
	
	@RequestMapping(value = "/NaverXdmCallback")
	public String naverXdmCallback() {
		return "xdm/naver/NaverXdmCallback";
	}
	
	public ResponseEntity<String> callNaverApi(String accessToken) {
		String url = "https://openapi.naver.com/v1/nid/me";

	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", "Bearer " + accessToken);

	    HttpEntity<String> entity = new HttpEntity<>(headers);

	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

	    return response;
	}
}
