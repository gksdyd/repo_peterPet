package com.peterpet.demo.module.kakao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/kakao/xdm")
public class KakaoController {
	
	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/KakaoXdmCallback")
	public String kakaoXdmCallback() {
		return "xdm/kakao/KakaoXdmCallback";
	}
	
	@ResponseBody
	@RequestMapping(value = "/KakaoXdmGetToken")
	public Map<String, Object> kakaoXdmResoponCode(@RequestBody KakaoDto dto, HttpServletRequest httpServletRequest,
			HttpSession httpSession) throws JsonMappingException, JsonProcessingException {
		String url = "https://kauth.kakao.com/oauth/token";

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "8c65df96425b66a2004460d521dbb912");
        params.add("redirect_uri", "http://localhost:8080/kakao/xdm/KakaoXdmCallback");
        params.add("code", dto.getCode());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
	    
        String responseBody = response.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(responseBody);
		String accessToken = rootNode.path("access_token").asText();
		
		Map<String, Object> rtMap = new HashMap<>();
		MemberDto memberDto = getKakaoUserInfo(accessToken);
		
		if (memberDto != null) {
			httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
			httpSession.setAttribute("sessSeqXdm", memberDto.getUserSeq());
			httpSession.setAttribute("sessIdXdm", memberDto.getUserId());
			httpSession.setAttribute("sessNameXdm", memberDto.getUserName());
			
			rtMap.put("rt", "success");
		} else {
			rtMap.put("rt", "fail");
		}
		
		return rtMap;
	}
	
	public MemberDto getKakaoUserInfo(String accessToken) throws JsonMappingException, JsonProcessingException {
	    String url = "https://kapi.kakao.com/v2/user/me";

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    headers.set("Authorization", "Bearer " + accessToken);

	    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
	    body.add("property_keys", "[\"kakao_account.email\",\"kakao_account.name\",\"kakao_account.profile\"]");
	    
	    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
	    
	    String responseBody = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode accountNode = objectMapper.readTree(responseBody).path("kakao_account");
		
		MemberDto dto = new MemberDto();
		dto.setUserName(accountNode.path("profile").path("nickname").asText());
		
		dto = memberService.kakaoLogin(dto);
	    return dto;
	}
}
