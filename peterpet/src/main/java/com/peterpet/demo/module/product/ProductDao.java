package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.peterpet.demo.module.base.BaseDao;
import com.peterpet.demo.module.base.BaseDto;
import com.peterpet.demo.module.member.MemberDto;

@Repository
public interface ProductDao extends BaseDao {

	public Integer selectMaxSeq();
	public int insert(ProductDto productDto);
	public List<ProductDto> selectList(ProductVo vo);
	public int selectOneCount(ProductVo vo);
	public int selectOneFeedCount(ProductVo vo);
	public int infoInsert(ProductDto productDto);
	public int funcInsert(ProductDto productDto);
	public ProductDto selectOnePoduct(ProductVo vo);
	public List<ProductDto> selectOneFunctions(ProductVo vo);
	public List<ProductDto> selectOneInfos(ProductVo vo);
	public int update(ProductDto productDto);
	public int infoUpdate(ProductDto productDto);
	public int funcUpdate(ProductDto productDto);
	public int delete(ProductDto productDto);
	public int funcDelete(ProductDto productDto);
	public int infoDelete(ProductDto productDto);
	public int uelete(ProductDto productDto);
	public int severalUelete(ProductVo vo);
	public int severalDelete(ProductVo vo);
	public List<ProductDto> selectFeedList(ProductVo vo);
	public int updateScore(MemberDto dto);
	public List<ProductDto> selectReview(ProductVo vo);
	public int insertUploaded(BaseDto dto);
	public int updateUploaded(BaseDto dto);
	public int selectReviewCount(ProductVo vo);
	public List<ProductDto> selectImage1(ProductVo vo);
	public List<ProductDto> selectImage2(ProductVo vo);
}
