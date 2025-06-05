package com.peterpet.demo.module.elastic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterpet.demo.module.base.BaseVo;
import com.peterpet.demo.module.base.Constants;

public class ElasticVo extends BaseVo {

	private String index;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
	public int totalDoc() throws JsonMappingException, JsonProcessingException {
		String url = Constants.LOCAL_ADDRESS + index + "/_search?pretty";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		String responseBody = response.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readTree(responseBody).path("hits").path("total").path("value").asInt();
	}
}
