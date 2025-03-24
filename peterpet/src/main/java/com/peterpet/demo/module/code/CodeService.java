package com.peterpet.demo.module.code;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peterpet.demo.module.codegroup.CodeGroupDto;

import jakarta.annotation.PostConstruct;

@Service
public class CodeService {

	@Autowired
	CodeDao codeDao;
	
	public List<CodeDto> selectList(CodeVo vo) {
		return codeDao.selectList(vo);
	}
	
	public CodeDto selectOne(CodeDto codeDto) {
		return codeDao.selectOne(codeDto);
	}
	
	public List<CodeDto> selectCodeGroupName() {
		return codeDao.selectCodeGroupName();
	}
	
	public Integer selectMaxSeq() {
		return codeDao.selectMaxSeq();
	}
	
	public int insert(CodeDto codeDto) {
		return codeDao.insert(codeDto);
	}
	
	public int selectOneCount(CodeVo vo) {
		return codeDao.selectOneCount(vo);
	}
	
	@PostConstruct
	public void selectListCachedCodeArrayList() throws Exception {
		List<CodeDto> codeListFromDb = (ArrayList<CodeDto>) codeDao.selectListCachedCodeArrayList();
		CodeDto.cachedCodeArrayList.clear(); 
		CodeDto.cachedCodeArrayList.addAll(codeListFromDb);
		System.out.println("cachedCodeArrayList: " + CodeDto.cachedCodeArrayList.size() + " chached !");
	}
    
    
	public static void clear() throws Exception {
		CodeDto.cachedCodeArrayList.clear();
	}
	
	
	public static List<CodeDto> selectListCachedCode(String cogrSeq) throws Exception {
		List<CodeDto> rt = new ArrayList<CodeDto>();
		for(CodeDto codeRow : CodeDto.cachedCodeArrayList) {
			if (codeRow.getCodeGroup_cogrSeq().equals(cogrSeq)) {
				rt.add(codeRow);
			} else {
				// by pass
			}
		}
		return rt;
	}

	
	public static String selectOneCachedCode(int code) throws Exception {
		String rt = "";
		for(CodeDto codeRow : CodeDto.cachedCodeArrayList) {
			if (codeRow.getCodeSeq().equals(Integer.toString(code))) {
				rt = codeRow.getCodeName();
			} else {
				// by pass
			}
		}
		return rt;
	}
}
