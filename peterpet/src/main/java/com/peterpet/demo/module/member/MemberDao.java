package com.peterpet.demo.module.member;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {

	List<MemberDto> selectList(MemberVo vo);
	int selectOneCount(MemberVo vo);
	MemberDto selectOne(MemberDto memberDto);
	MemberDto selectOneLogin(MemberDto memberDto);
}
