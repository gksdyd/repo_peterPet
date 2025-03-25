package com.peterpet.demo.module.code;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.codegroup.CodeGroupDto;

@Repository
public interface CodeDao {

	public List<CodeDto> selectList(CodeVo vo);
	public CodeDto selectOne(CodeDto codeDto);
	public List<CodeDto> selectCodeGroupName();
	public Integer selectMaxSeq();
	public int insert(CodeDto codeDto);
	public int selectOneCount(CodeVo vo);
	public List<CodeDto> selectListCachedCodeArrayList();
	public int update(CodeDto codeDto);
}
