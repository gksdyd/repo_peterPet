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
	
	public int insert(ProductVo productVo) {
		return productDao.insert(productVo);
	}
}
