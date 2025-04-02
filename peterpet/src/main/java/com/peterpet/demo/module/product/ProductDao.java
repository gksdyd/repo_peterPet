package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {

	public Integer selectMaxSeq();
	public int insert(ProductDto productDto);
	public List<ProductDto> selectList(ProductVo vo);
	public int selectOneCount(ProductVo vo);
	public String selectOneFunc(ProductVo vo);
	public int infoInsert(ProductDto productDto);
}
