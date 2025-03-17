package com.peterpet.demo.module.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peterpet.demo.module.codegroup.CodeGroupDto;

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
	
	public CodeDto selectMaxSeq() {
		return codeDao.selectMaxSeq();
	}
	
	public int insert(CodeDto codeDto) {
		return codeDao.insert(codeDto);
	}
	
	public int selectOneCount() {
		return codeDao.selectOneCount();
	}
}
