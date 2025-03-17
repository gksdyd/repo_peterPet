package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	public List<ProductDto> selectProductType() {
		return productDao.selectProductType();
	}
	
	public List<ProductDto> selectProductCate() {
		return productDao.selectProductCate();
	}
	
	public List<ProductDto> selectProductFilt(ProductDto productDto) {
		return productDao.selectProductFilt(productDto);
	}
}
