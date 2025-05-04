package com.peterpet.demo.module.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;
import com.peterpet.demo.module.base.BaseDto;
import com.peterpet.demo.module.product.ProductVo;

@Repository
public interface MemberDao extends BaseDao {

	public List<MemberDto> selectList(MemberVo vo);
	public int selectOneCount(MemberVo vo);
	public MemberDto selectOne(MemberDto memberDto);
	public MemberDto selectOne(ProductVo productVo);
	public MemberDto selectOneLogin(MemberDto memberDto);
	public int update(MemberDto memberDto);
	public int insert(MemberDto memberDto);
	public int deliverInsert(MemberDto memberDto);
	public int checkId(MemberDto memberDto);
	public int checkEmail(MemberDto memberDto);
	public int updatePassword(MemberDto memberDto);
	public int uelete(MemberDto memberDto);
	public MemberDto checkPhone(MemberDto memberDto);
	public int reviewInsert(MemberDto memberDto);
	public int insertUploaded(BaseDto dto);
	public List<MemberDto> reviewList(MemberVo memberVo);
	public int reviewCount(MemberVo vo);
	public MemberDto selectOneReview(MemberVo memberVo);
	public int updateReview(MemberDto memberDto);
	public int deleteImage(MemberDto memberDto);
	public int deleteReview(MemberDto memberDto);
}
