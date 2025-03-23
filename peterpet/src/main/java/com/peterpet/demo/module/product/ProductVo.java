package com.peterpet.demo.module.product;

import com.peterpet.demo.module.base.BaseVo;

public class ProductVo extends BaseVo{

	private int prodType;

	public int getProdType() {
		return prodType;
	}

	public void setProdType(int prodType) {
		this.prodType = prodType;
	}
	
	public void InitProdType() {
		if (getProdType() == 0) {
			setProdType(1);
		}
	}
}
