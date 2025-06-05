package com.peterpet.demo.module.elastic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterpet.demo.module.base.Constants;

@Controller
@RequestMapping(value = "/elastic/xdm")
public class ElasticController {

	@RequestMapping(value = "/ElasticXdmList")
	public String elasticXdmList(Model model) {
		String url = Constants.LOCAL_ADDRESS + "_cat/indices?v";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		String[] responseBody = response.getBody().split("\n");
		
		List<ElasticDto> dtos = new ArrayList<>();
		int index = 0;
		for (int i = 0; i < responseBody.length; i++) {
			ElasticDto dto = new ElasticDto();
			dto.setContents(responseBody[i].split(" "));
			
			if (i == 0) {
				index = dto.getContents().indexOf("index");
			} else {
				dto.setIndex(dto.getContents().get(index));
				dtos.add(dto);
			}
		}
		
		model.addAttribute("dtos", dtos);
		return "xdm/elastic/ElasticXdmList";
	}
	
	@RequestMapping(value = "/ElasticXdmIndexSearch")
	public String elasticXdmIndexSearch(Model model) {
		String url = Constants.LOCAL_ADDRESS + "_cat/indices?v";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		String[] responseBody = response.getBody().split("\n");
		
		List<ElasticDto> dtos = new ArrayList<>();
		for (int i = 0; i < responseBody.length; i++) {
			ElasticDto dto = new ElasticDto();
			dto.setContents(responseBody[i].split(" "));
			dtos.add(dto);
		}
		
		model.addAttribute("dtos", dtos);
		return "xdm/elastic/ElasticXdmContents";
	}
	
	@RequestMapping(value = "/ElasticXdmDocSearch")
	public String elasticXdmDocSearch(Model model, @ModelAttribute("vo") ElasticVo vo) 
			throws JsonMappingException, JsonProcessingException {
		String url = Constants.LOCAL_ADDRESS + vo.getIndex() + "/_search?pretty&from=" + (5 * (vo.getThisPage() - 1)) + "&size=5";
		
		vo.setParamsPaging(vo.totalDoc());
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		String responseBody = response.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode docNode = objectMapper.readTree(responseBody).path("hits").path("hits");
		
		List<ElasticDto> dtos = new ArrayList<>();
		for (int i = 0; i < docNode.size(); i++) {
			JsonNode docContentsNode = docNode.get(i).path("_source");
			ElasticDto dto = new ElasticDto();
			
			dto.setId(docContentsNode.path("id").asText());
			dto.setName(docContentsNode.path("name").asText());
			dto.setEngName(docContentsNode.path("engName").asText());
			dto.setUrl(docContentsNode.path("url").asText());
			dto.setType(docContentsNode.path("type").asText());
			dto.setBrand(docContentsNode.path("brand").asText());
			
			dtos.add(dto);
		}
		
		model.addAttribute("dtos", dtos);
		return "xdm/elastic/ElasticXdmDocContents";
	}
	
	@RequestMapping(value = "/ElasticXdmForm")
	public String elasticXdmForm(Model model) {
		String url = Constants.LOCAL_ADDRESS + "_cat/indices?v";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		String[] responseBody = response.getBody().split("\n");
		
		List<ElasticDto> dtos = new ArrayList<>();
		int index = 0;
		for (int i = 0; i < responseBody.length; i++) {
			ElasticDto dto = new ElasticDto();
			dto.setContents(responseBody[i].split(" "));
			
			if (i == 0) {
				index = dto.getContents().indexOf("index");
			} else {
				if (dto.getContents().get(index).contains("peter")) {
					dto.setIndex(dto.getContents().get(index));
					dtos.add(dto);					
				}
			}
		}
		
		model.addAttribute("dtos", dtos);
		return "xdm/elastic/ElasticXdmForm";
	}
	
	@RequestMapping(value = "/ElasticXdmIndexChange")
	public String elasticXdmIndexChange(ElasticDto dto, Model model) 
			throws JsonMappingException, JsonProcessingException {
		String url = Constants.LOCAL_ADDRESS + dto.getIndex() + "/_search?pretty";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		
		String responseBody = response.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();

		model.addAttribute("index", dto.getIndex());
		model.addAttribute("num", objectMapper.readTree(responseBody).path("hits").path("total").path("value").asInt() + 1);
		
		return "/xdm/elastic/ElasticXdmPeterPet";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ElasticXdmDocRegister")
	public void elasticXdmDocRegister(ElasticDto dto) 
			throws JsonMappingException, JsonProcessingException {
		String url = Constants.LOCAL_ADDRESS + dto.getIndex() + "/_doc/" + dto.getId();
		
		String json = "{\"id\":\"" + dto.getId() + 
				"\", \"name\":\"" + dto.getName() + 
				"\", \"type\":\"" + dto.getType() + 
				"\", \"brand\":\"" + dto.getBrand() + "\"}";
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<String> request = new HttpEntity<>(json, headers);
	    
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange(
	        url, 
	        HttpMethod.PUT, 
	        request, 
	        String.class
	    );
	    
	    System.out.println("Response: " + response.getBody());
	}
	
	@ResponseBody
	@RequestMapping(value = "/ElasticXdmIndexRegister")
	public void elasticXdmIndexRegister(ElasticDto dto) 
			throws JsonMappingException, JsonProcessingException {
		String url = Constants.LOCAL_ADDRESS + dto.getIndex();
	    
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange(
	        url, 
	        HttpMethod.PUT, 
	        request,
	        String.class
	    );
	    
	    System.out.println("Response: " + response.getBody());
	}
	
	@ResponseBody
	@RequestMapping(value = "/ElasticXdmIndexDelete")
	public void elasticXdmIndexDelete(ElasticDto dto) 
			throws JsonMappingException, JsonProcessingException {
		String url = Constants.LOCAL_ADDRESS + dto.getIndex();
	    
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange(
	        url, 
	        HttpMethod.DELETE, 
	        request,
	        String.class
	    );
	    
	    System.out.println("Response: " + response.getBody());
	}
	
	@ResponseBody
	@RequestMapping(value = "/ElasticXdmDocumentDelete")
	public void elasticXdmDocumentDelete(ElasticDto dto) 
			throws JsonMappingException, JsonProcessingException {
		String url = Constants.LOCAL_ADDRESS + dto.getIndex() + "/_doc/" + dto.getId();
	    
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<String> response = restTemplate.exchange(
	        url, 
	        HttpMethod.DELETE, 
	        request,
	        String.class
	    );
	    
	    System.out.println("Response: " + response.getBody());
	}
	
	@RequestMapping(value = "/ElasticXdmTypeBrand")
	public String elasticXdmTypeBrand(@RequestParam(value = "type") int type, Model model) {
		model.addAttribute("type", type);
		return "xdm/elastic/ElasticXdmBrand";
	}
}
