package com.peterpet.demo.module.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;
	
	public String selectMaxSeq() {
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
}
