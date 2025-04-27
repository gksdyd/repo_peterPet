package com.peterpet.demo.module.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;
import com.peterpet.demo.module.base.BaseDto;

@Repository
public interface MemberDao extends BaseDao {

	public List<MemberDto> selectList(MemberVo vo);
	public int selectOneCount(MemberVo vo);
	public MemberDto selectOne(MemberDto memberDto);
	public MemberDto selectOneLogin(MemberDto memberDto);
	public int update(MemberDto memberDto);
	public int insert(MemberDto memberDto);
	public int checkId(MemberDto memberDto);
	public int checkEmail(MemberDto memberDto);
	public int updatePassword(MemberDto memberDto);
	public int uelete(MemberDto memberDto);
	public MemberDto checkPhone(MemberDto memberDto);
	public int reviewInsert(MemberDto memberDto);
	public int insertUploaded(BaseDto dto);
}
