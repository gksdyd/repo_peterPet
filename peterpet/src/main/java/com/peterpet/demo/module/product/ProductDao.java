package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {

	public List<ProductDto> selectProductType();
	public List<ProductDto> selectProductCate();
	public List<ProductDto> selectProductFilt(ProductDto productDto);
}
