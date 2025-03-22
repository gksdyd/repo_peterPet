package com.peterpet.demo.module.product;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {

	public String selectMaxSeq();
	public int insert(ProductVo productVo);
}
