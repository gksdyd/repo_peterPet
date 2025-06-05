package com.peterpet.demo.module.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterpet.demo.module.base.BaseController;
import com.peterpet.demo.module.base.Constants;
import com.peterpet.demo.module.code.CodeDto;
import com.peterpet.demo.module.delivery.DeliveryService;
import com.peterpet.demo.module.elastic.ElasticDto;
import com.peterpet.demo.module.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/peter/shop")
public class ShopPeterController extends BaseController {

	@Autowired
	ProductService productService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	DeliveryService deliveryService;
	
	@Value("${image-key}")
	private String imageKey;
	
	@RequestMapping(value = "/ShopPeterList")
	public String shopPeterList(@ModelAttribute("vo") ProductVo vo, Model model) {
		vo.setRowNumToShow(9);
		vo.setParamsPaging(productService.selectOneFeedCount(vo));
		List<ProductDto> dtos = productService.selectFeedList(vo);
		
		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).calculatePrice();
		}
		model.addAttribute("list", dtos);
		model.addAttribute("code", CodeDto.cachedCodeArrayList);
		return "peter/shop/ShopPeterList";
	}
	
	@RequestMapping(value = "/ShopPeterView")
	public String shopPeterView(@ModelAttribute("vo") ProductVo vo, Model model, HttpSession httpSession) throws Exception {
		if (httpSession.getAttribute("sessSeqPeter") != null) {
			vo.setUserSeq(httpSession.getAttribute("sessSeqPeter").toString());			
		}
		
		ProductDto dto = productService.selectOnePoduct(vo);
		if (vo.getProdType() != 3) {
			dto.setWeightArray(dto.getWeightArr().split(","));			
		}
		model.addAttribute("item", dto);
		vo.setParamsPaging(productService.selectReviewCount(vo));
		model.addAttribute("list", productService.selectReview(vo));
		model.addAttribute("image1", productService.selectImage1(vo));
		model.addAttribute("image2", productService.selectImage2(vo));
		model.addAttribute("code", CodeDto.cachedCodeArrayList);
		model.addAttribute("imageKey", imageKey);
		
		List<ProductDto> dtos = productService.selectSameProducts(vo);
		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).calculatePrice();
		}
		model.addAttribute("sameProd", dtos);
		deliveryTimeCheck(vo);
		return "peter/shop/ShopPeterView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ShopPeterTimeProc")
	public Map<String, Object> shopPeterTimeProc(@ModelAttribute("vo") ProductVo vo) throws Exception {
		Map<String, Object> rtMap = new HashMap<>();
		deliveryTimeCheck(vo);
		rtMap.put("day", vo.getDiffDay());
		rtMap.put("hour", vo.getDiffHour());
		rtMap.put("minute", vo.getDiffMinute());
		rtMap.put("second", vo.getDiffSecond());
		rtMap.put("delivery", vo.getDeliveryTime());
		return rtMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ShopPeterInfoProc")
	public Map<String, Object> shopPeterInfoProc(ProductVo vo) {
		Map<String, Object> rtMap = new HashMap<>();
		vo.setRowNumToShow(9);
		if (vo.getProdFuncArray().size() > 0) {
			if (vo.getProdFuncArray().get(0).equals("")) {
				vo.getProdFuncArray().remove(0);				
			}
		}
		vo.setParamsPaging(productService.selectOneFeedCount(vo));
		rtMap.put("vo", vo);
		rtMap.put("dtos", productService.selectFeedList(vo));
		return rtMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ReviewPeterProc")
	public Map<String, Object> reviewPeterProc(ProductVo vo) {
		Map<String, Object> rtMap = new HashMap<>();
		vo.setParamsPaging(productService.selectReviewCount(vo));
		rtMap.put("vo", vo);
		rtMap.put("list", productService.selectReview(vo));
		return rtMap;
	}
	
	@RequestMapping(value = "/ShopPeterPayment")
	public String shopPeterPayment(@ModelAttribute("vo") ProductVo vo, Model model) {
		model.addAttribute("code", CodeDto.cachedCodeArrayList);
		model.addAttribute("user", memberService.selectOne(vo));
		model.addAttribute("delivery", deliveryService.selectMain(vo));
		model.addAttribute("list", deliveryService.selectList(vo));
		model.addAttribute("prod", productService.selectPurchase(vo));
		return "peter/shop/ShopPeterPayment";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ShopPeterChangeWeight")
	public Map<String, Object> shopPeterChangeWeight(ProductVo vo) {
		Map<String, Object> rtMap = new HashMap<>();
		rtMap.put("item", productService.selectFeedInfo(vo));
		return rtMap;
	}
	
	@RequestMapping(value = "/ShopPeterSearch")
	public String shopPeterSearch(@ModelAttribute("vo") ProductVo vo, Model model) {
		vo.setRowNumToShow(9);
		vo.setParamsPaging(productService.selectOneFeedCount(vo));
		List<ProductDto> dtos = productService.selectFeedList(vo);
		
		for (int i = 0; i < dtos.size(); i++) {
			dtos.get(i).calculatePrice();
		}
		model.addAttribute("list", dtos);
		return "peter/include/shop :: shop";
	}
	
	@RequestMapping(value = "/ShopPeterFilter")
	public String shopPeterFilter(@ModelAttribute("vo") ProductVo vo) {
		return "peter/include/filter :: filter";
	}
	
	@RequestMapping(value = "/ShopPeterSupplyProc")
	public String productXdmSupplyProc(@ModelAttribute("vo") ProductVo vo) {
		return "peter/include/supplies";
	}
	
	@RequestMapping(value = "/SearchPeterProduct")
	public String searchPeterProduct(ElasticDto dto, Model model) 
			throws JsonMappingException, JsonProcessingException {
		String url = Constants.LOCAL_ADDRESS + "peterpet/_search?pretty";
		
		String json = null;
		if (!dto.getType().equals("00")) {
			json = String.format("""
				{
				  "query": {
				    "bool": {
				      "must": [
				        { "match": { "type": "%s" }},
				        { "regexp": { "name": ".*%s.*" }}
				      ]
				    }
				  }
				}
				""", dto.getType(), dto.getName());	
		} else {
			json = String.format("""
					{
					  "query": {
					    "regexp": {
					      "name": ".*%s.*"
					    }
					  }
					}
					""", dto.getName());	
		}

		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    HttpEntity<String> request = new HttpEntity<>(json, headers);
	    
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		
		String responseBody = response.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode documentNode = objectMapper.readTree(responseBody).path("hits").path("hits");
		
		ProductVo vo = new ProductVo();
		for (int i = 0; i < documentNode.size(); i++) {
			vo.getSearchArray().add(documentNode.get(i).path("_source").path("seq").asText());
		}
		
		if (vo.getSearchArray().size() > 0) {
			model.addAttribute("list", productService.searchList(vo));
		}
		
		return "peter/shop/ShopPeterSearchList";
	}
}
