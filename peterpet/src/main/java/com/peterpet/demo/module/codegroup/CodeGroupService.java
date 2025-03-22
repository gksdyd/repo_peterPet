package com.peterpet.demo.module.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {

	@Autowired
	CodeGroupDao codeGroupDao;
	
	public List<CodeGroupDto> selectList(CodeGroupVo vo) {
		return codeGroupDao.selectList(vo);
	}
	
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto) {
		return codeGroupDao.selectOne(codeGroupDto);
	}
	
	public String selectMaxSeq() {
		return codeGroupDao.selectMaxSeq();
	}
	
	public int insert(CodeGroupDto codeGroupDto) {
		return codeGroupDao.insert(codeGroupDto);
	}
	
	public int selectOneCount() {
		return codeGroupDao.selectOneCount();
	}
}
