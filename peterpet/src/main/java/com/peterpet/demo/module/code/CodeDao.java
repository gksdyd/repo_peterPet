package com.peterpet.demo.module.code;

import java.util.List;

import org.springframework.stereotype.Repository;

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
	public int uelete(CodeDto codeDto);
	public int delete(CodeDto codeDto);
	public int severalUelete(CodeVo vo);
	public int severalDelete(CodeVo vo);
}
