package com.peterpet.demo.module.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.peterpet.demo.module.base.BaseService;

@Service
public class MemberService extends BaseService {

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	public List<MemberDto> selectList(MemberVo vo) {
		return memberDao.selectList(vo);
	}
	
	public int selectOneCount(MemberVo vo) {
		return memberDao.selectOneCount(vo);
	}
	
	public MemberDto selectOne(MemberDto memberDto) {
		return memberDao.selectOne(memberDto);
	}
	
	public MemberDto selectOneLogin(MemberDto memberDto) {
		return memberDao.selectOneLogin(memberDto);
	}
	
	public int update(MemberDto memberDto) {
		return memberDao.update(memberDto);
	}
	
	public int insert(MemberDto memberDto) {
		return memberDao.insert(memberDto);
	}
	
	public int checkId(MemberDto memberDto) {
		return memberDao.checkId(memberDto);
	}
	
	public int checkEmail(MemberDto memberDto) {
		return memberDao.checkEmail(memberDto);
	}
	
	public int updatePassword(MemberDto memberDto) {
		return memberDao.updatePassword(memberDto);
	}
	
	public int uelete(MemberDto memberDto) {
		return memberDao.uelete(memberDto);
	}
	
	public MemberDto checkPhone(MemberDto memberDto) {
		return memberDao.checkPhone(memberDto);
	}
	
	public int reviewInsert(MemberDto memberDto) throws Exception {
		memberDao.reviewInsert(memberDto);
		uploadFilesToS3(memberDto.getUploadImg1(), memberDto, "image", memberDto.getUploadImg1Type(), memberDto.getUploadImg1MaxNumber()
    			, memberDto.getReviSeq(), memberDao, amazonS3Client);
		return 1;
	}
}
