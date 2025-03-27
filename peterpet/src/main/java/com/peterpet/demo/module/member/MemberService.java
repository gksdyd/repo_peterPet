package com.peterpet.demo.module.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDao memberDao;
	
	List<MemberDto> selectList(MemberVo vo) {
		return memberDao.selectList(vo);
	}
	
	int selectOneCount(MemberVo vo) {
		return memberDao.selectOneCount(vo);
	}
	
	MemberDto selectOne(MemberDto memberDto) {
		return memberDao.selectOne(memberDto);
	}
	
	MemberDto selectOneLogin(MemberDto memberDto) {
		return memberDao.selectOneLogin(memberDto);
	}
}
