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
	
	int selectOneCount() {
		return memberDao.selectOneCount();
	}
	
	MemberDto selectOne(MemberDto memberDto) {
		return memberDao.selectOne(memberDto);
	}
}
