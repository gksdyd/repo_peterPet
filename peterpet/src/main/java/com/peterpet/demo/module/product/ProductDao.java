package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {

	public Integer selectMaxSeq();
	public int insert(ProductDto productDto);
	public List<ProductDto> selectList(ProductVo vo);
	public int selectOneCount(ProductVo vo);
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
}
