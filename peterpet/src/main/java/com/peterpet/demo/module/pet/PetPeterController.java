package com.peterpet.demo.module.pet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peterpet.demo.module.code.CodeService;

@Controller
@RequestMapping(value = "/peter/pet")
public class PetPeterController {

	@Autowired
	PetService petService;
	
	@ResponseBody
	@RequestMapping(value = "/PetPeterProc")
	public Map<String, Object> petPeterProc(PetVo vo, Model model) throws Exception {
		Map<String, Object> rtMap = new HashMap<>();
		
		PetDto petDto = petService.selectOne(vo);
		
		rtMap.put("variety", CodeService.selectOneCachedCode(petDto.getPetVarieties()));
		rtMap.put("gender", CodeService.selectOneCachedCode(petDto.getPetGender()));
		rtMap.put("dto", petDto);
		
		List<String> personal = new ArrayList<>();
		List<PetDto> petDtos = petService.selectOnePersonal(vo);
		for (int i = 0; i < petDtos.size(); i++) {
			personal.add(CodeService.selectOneCachedCode(petDtos.get(i).getPersDiscription()));
		}
		rtMap.put("personal", personal);
		
		List<String> disease = new ArrayList<>();
		petDtos = petService.selectOneDisease(vo);
		for (int i = 0; i < petDtos.size(); i++) {
			disease.add(CodeService.selectOneCachedCode(petDtos.get(i).getDiseDiscription()));
		}
		rtMap.put("disease", disease);
		return rtMap;
	}
}
