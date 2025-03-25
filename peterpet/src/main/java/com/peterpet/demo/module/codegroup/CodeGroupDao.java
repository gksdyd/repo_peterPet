package com.peterpet.demo.module.codegroup;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CodeGroupDao {

	public List<CodeGroupDto> selectList(CodeGroupVo vo);
	public CodeGroupDto selectOne(CodeGroupDto codeGroupDto);
	public int insert(CodeGroupDto codeGroupDto);
	public Integer selectMaxSeq();
	public int selectOneCount(CodeGroupVo vo);
	public int update(CodeGroupDto codeGroupDto);
	public int uelete(CodeGroupDto codeGroupDto);
	public int delete(CodeGroupDto codeGroupDto);
}
