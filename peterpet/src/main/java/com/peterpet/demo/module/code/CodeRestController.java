package com.peterpet.demo.module.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/xdm/code")
public class CodeRestController {

	@Autowired
	CodeService codeService;
	
	@GetMapping(value = "/RestXdmList")
	public List<CodeDto> restXdmList(CodeVo vo) throws JsonMappingException, JsonProcessingException {
		if (!validationToken(getToken())) {
			return null;
		}
		vo.setParamsPaging(codeService.selectOneCount(vo));
		return codeService.selectList(vo);
	}
	
	@GetMapping(value = "/RestXdmOneSelect")
	public CodeDto restXdmList(CodeDto dto) throws JsonMappingException, JsonProcessingException {
		if (!validationToken(getToken())) {
			return null;
		}
		return codeService.selectOne(dto);
	}
	
	@PostMapping(value = "/RestXdmInsert")
	public int restXdmInsert(CodeDto dto) throws JsonMappingException, JsonProcessingException {
		if (!validationToken(getToken())) {
			return 0;
		}
		codeService.insert(dto);
		return Integer.parseInt(dto.getCodeSeq());
	}
	
	@PostMapping(value = "/RestXdmDelete")
	public int restXdmDelete(CodeDto dto) throws JsonMappingException, JsonProcessingException {
		if (!validationToken(getToken())) {
			return 0;
		}
		return codeService.delete(dto);
	}
	
	public String getToken() throws JsonMappingException, JsonProcessingException {
		String url = "http://3.38.103.31:3000/getToken";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		String responseBody = response.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readTree(responseBody).path("token").asText();
	}
	
	public boolean validationToken(String token) throws JsonMappingException, JsonProcessingException {
		String url = "http://3.38.103.31:3000/validToken";
		
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", token);
	    
	    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null, headers);
	    
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				entity,
				String.class
		);
		
		String responseBody = response.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();
		if (objectMapper.readTree(responseBody).path("data").path("response").asText().equals("good")) {
			return true;
		}
		return false;
	}
}
