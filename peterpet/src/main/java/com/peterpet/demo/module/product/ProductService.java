package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.peterpet.demo.module.base.BaseService;
import com.peterpet.demo.module.member.MemberDto;
import com.peterpet.demo.module.wishlist.WishlistVo;

@Service
public class ProductService extends BaseService {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	public Integer selectMaxSeq() {
		return productDao.selectMaxSeq();
	}
	
	public int insert(ProductDto productDto) {
		return productDao.insert(productDto);
	}
	
	public List<ProductDto> selectList(ProductVo vo) {
		return productDao.selectList(vo);
	}
	
	public int selectOneCount(ProductVo vo) {
		return productDao.selectOneCount(vo);
	}
	
	public int infoInsert(ProductDto productDto) {
		return productDao.infoInsert(productDto);
	}
	
	public int funcInsert(ProductDto productDto) {
		return productDao.funcInsert(productDto);
	}
	
	public ProductDto selectOnePoduct(ProductVo vo) {
		return productDao.selectOnePoduct(vo);
	}
	
	public List<ProductDto> selectOneFunctions(ProductVo vo) {
		return productDao.selectOneFunctions(vo);
	}
	
	public List<ProductDto> selectOneInfos(ProductVo vo) {
		return productDao.selectOneInfos(vo);
	}
	
	public int update(ProductDto productDto) {			
		return productDao.update(productDto);
	}
	
	public int infoUpdate(ProductDto productDto) {
		return productDao.infoUpdate(productDto);
	}
	
	public int funcUpdate(ProductDto productDto) {
		return productDao.funcUpdate(productDto);
	}
	
	public int delete(ProductDto productDto) {
		return productDao.delete(productDto);
	}
	
	public int funcDelete(ProductDto productDto) {
		return productDao.funcDelete(productDto);
	}
	
	public int infoDelete(ProductDto productDto) {
		return productDao.infoDelete(productDto);
	}
	
	public int uelete(ProductDto productDto) {
		return productDao.uelete(productDto);
	}
	
	public int severalUelete(ProductVo vo) {
		return productDao.severalUelete(vo);
	}
	
	public int severalDelete(ProductVo vo) {
		return productDao.severalDelete(vo);
	}
	
	public List<ProductDto> selectFeedList(ProductVo vo) {
		return productDao.selectFeedList(vo);
	}
	
	public int selectOneFeedCount(ProductVo vo) {
		return productDao.selectOneFeedCount(vo);
	}
	
	public int updateScore(MemberDto dto) {
		return productDao.updateScore(dto);
	}
	
	public List<ProductDto> selectReview(ProductVo vo) {
		List<ProductDto> dtos = productDao.selectReview(vo);
		for(ProductDto dto : dtos) {
			if (dto.getTemp() != null) {
				dto.setPaths(dto.getTemp().split(","));				
			}
			StringBuffer str = new StringBuffer(dto.getReviText());
			for (int j = 100; j < str.length(); j += 101) {
				str.insert(j, "\n");			
			}
			dto.setReviText(str.toString());
		}
		return dtos;
	}
	
	public int selectReviewCount(ProductVo vo) {
		return productDao.selectReviewCount(vo);
	}
	
	public List<ProductDto> selectImage1(ProductVo vo) {
		return productDao.selectImage1(vo);
	}
	
	public List<ProductDto> selectImage2(ProductVo vo) {
		return productDao.selectImage2(vo);
	}
	
	public void imageInsert(ProductDto productDto) throws Exception {
		uploadFilesToS3(productDto.getUploadImg1(), productDto, "image", productDto.getUploadImg1Type(), productDto.getUploadImg1MaxNumber()
    			, productDto.getProdSeq(), productDao, amazonS3Client);
	}
	
	public int updateUploaded(ProductDto productDto) {
		return productDao.updateUploaded(productDto);
	}
	
	public ProductDto selectPurchase(ProductVo vo) {
		return productDao.selectPurchase(vo);
	}
	
	public ProductDto selectFeedInfo(ProductVo vo) {
		return productDao.selectFeedInfo(vo);
	}
	
	public List<ProductDto> selectSameProducts(ProductVo vo) {
		return productDao.selectSameProducts(vo);
	}
	
	public List<ProductDto> selectAllProducts() {
		return productDao.selectAllProducts();
	}
	
	public List<ProductDto> selectWishlists(ProductVo vo) {
		return productDao.selectWishlists(vo);
	}
	
	public List<ProductDto> selectWishlists(WishlistVo vo) {
		return productDao.selectWishlists(vo);
	}
}
