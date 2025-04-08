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

import com.peterpet.demo.common.common.Constants;
import com.peterpet.demo.module.code.CodeService;

import jakarta.servlet.http.HttpSession;

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
		
		rtMap.put("type", CodeService.selectOneCachedCode(petDto.getPetType()));
		rtMap.put("variety", CodeService.selectOneCachedCode(petDto.getPetVarieties()));
		rtMap.put("gender", CodeService.selectOneCachedCode(petDto.getPetGender()));
		rtMap.put("dto", petDto);
		
		List<String> personal = new ArrayList<>();
		List<PetDto> petDtos = petService.selectOnePersonal(vo);
		for (int i = 0; i < petDtos.size(); i++) {
			personal.add(CodeService.selectOneCachedCode(petDtos.get(i).getPersDiscription()));
		}
		rtMap.put("personal", personal);
		rtMap.put("personalDto", petDtos);
		
		List<String> disease = new ArrayList<>();
		petDtos = petService.selectOneDisease(vo);
		for (int i = 0; i < petDtos.size(); i++) {
			disease.add(CodeService.selectOneCachedCode(petDtos.get(i).getDiseDiscription()));
		}
		rtMap.put("disease", disease);
		rtMap.put("diseaseDto", petDtos);
		return rtMap;
	}
	
	@RequestMapping(value = "/InsertPeterForm")
	public String insertPeterForm(PetDto petDto, HttpSession httpSession) {
		petDto.setUser_userSeq((String) httpSession.getAttribute("sessSeqXdm"));
		petService.insert(petDto);
		
		petDto.setPetSeq(petService.selectMaxSeq());
		for (int i = 0; i < petDto.getPetPersonalArray().size(); i++) {
			petDto.setPersDiscription(petDto.getPetPersonalArray().get(i));
			petService.personalInsert(petDto);
		}
		
		for (int i = 0; i < petDto.getPetDiseaseArray().size(); i++) {
			petDto.setDiseDiscription(petDto.getPetDiseaseArray().get(i));
			petService.diseaseInsert(petDto);
		}
		return "redirect:/peter/member/MyAccountPeterForm";
	}
	
	@RequestMapping(value = "/UpdatePeterForm")
	public String updatePeterForm(PetDto petDto, HttpSession httpSession) {
		int size;
		int flag;
		
		petService.update(petDto);
		
		PetVo vo = new PetVo();
		vo.setPetSeq(petDto.getPetSeq());
		
		List<PetDto> dtos = petService.selectOnePersonal(vo);
		// 반려동물의 성격 갯수 증가, 감소 여부 체크
		if (dtos.size() > petDto.getPetPersonalArray().size()) {
			size = dtos.size();
			flag = Constants.PET_UPDATE_COUNT_DECREASE;
		} else if (dtos.size() < petDto.getPetPersonalArray().size()) {
			size = petDto.getPetPersonalArray().size();
			flag = Constants.PET_UPDATE_COUNT_INCREASE;
		} else {
			size = dtos.size();
			flag = Constants.PET_UPDATE_COUNT_MAINTAIN;
		}
		
		// 반려동물의 성격 갯수 증가, 감소에 따른 insert, update, delete
		for (int i = 0; i < size; i++) {
			if (flag == Constants.PET_UPDATE_COUNT_DECREASE) {
				if (i < petDto.getPetPersonalArray().size()) {
					petDto.setPersSeq(dtos.get(i).getPersSeq());
					petDto.setPersDiscription(petDto.getPetPersonalArray().get(i));
					petService.updatePersonal(petDto);				
				} else {
					petDto.setPersSeq(dtos.get(i).getPersSeq());
					petService.deletePersonal(petDto);	
				}
			} else if (flag == Constants.PRODUCT_UPDATE_COUNT_INCREASE) {
				if (i < dtos.size()) {
					petDto.setPersSeq(dtos.get(i).getPersSeq());
					petDto.setPersDiscription(petDto.getPetPersonalArray().get(i));
					petService.updatePersonal(petDto);					
				} else {
					petDto.setPersDiscription(petDto.getPetPersonalArray().get(i));
					petService.personalInsert(petDto);	
				}
			} else if (flag == Constants.PRODUCT_UPDATE_COUNT_MAINTAIN) {
				petDto.setPersSeq(dtos.get(i).getPersSeq());
				petDto.setPersDiscription(petDto.getPetPersonalArray().get(i));
				petService.updatePersonal(petDto);	
			}
		}
		
		dtos = petService.selectOneDisease(vo);
		// 반려동물의 질병 갯수 증가, 감소 여부 체크
		if (dtos.size() > petDto.getPetDiseaseArray().size()) {
			size = dtos.size();
			flag = Constants.PET_UPDATE_COUNT_DECREASE;
		} else if (dtos.size() < petDto.getPetDiseaseArray().size()) {
			size = petDto.getPetDiseaseArray().size();
			flag = Constants.PET_UPDATE_COUNT_INCREASE;
		} else {
			size = dtos.size();
			flag = Constants.PET_UPDATE_COUNT_MAINTAIN;
		}
		
		// 반려동물의 성격 갯수 증가, 감소에 따른 insert, update, delete
		for (int i = 0; i < size; i++) {
			if (flag == Constants.PET_UPDATE_COUNT_DECREASE) {
				if (i < petDto.getPetDiseaseArray().size()) {
					petDto.setDiseSeq(dtos.get(i).getDiseSeq());
					petDto.setDiseDiscription(petDto.getPetDiseaseArray().get(i));
					petService.updateDisease(petDto);				
				} else {
					petDto.setDiseSeq(dtos.get(i).getDiseSeq());
					petService.deleteDisease(petDto);	
				}
			} else if (flag == Constants.PRODUCT_UPDATE_COUNT_INCREASE) {
				if (i < dtos.size()) {
					petDto.setDiseSeq(dtos.get(i).getDiseSeq());
					petDto.setDiseDiscription(petDto.getPetDiseaseArray().get(i));
					petService.updateDisease(petDto);					
				} else {
					petDto.setDiseDiscription(petDto.getPetDiseaseArray().get(i));
					petService.diseaseInsert(petDto);	
				}
			} else if (flag == Constants.PRODUCT_UPDATE_COUNT_MAINTAIN) {
				petDto.setDiseSeq(dtos.get(i).getDiseSeq());
				petDto.setDiseDiscription(petDto.getPetDiseaseArray().get(i));
				petService.updateDisease(petDto);
			}
		}
		
		return "redirect:/peter/member/MyAccountPeterForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/PetPeterDeltProc")
	public void petPeterDeltProc(PetVo vo) {
		petService.uelete(vo);
	}
}
