package com.peterpet.demo.module.wishlist;

import com.peterpet.demo.module.base.BaseVo;

public class WishlistVo extends BaseVo {

	private String userSeq;
	private String prodSeq;
	private String wishSeq;
	
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getProdSeq() {
		return prodSeq;
	}
	public void setProdSeq(String prodSeq) {
		this.prodSeq = prodSeq;
	}
	public String getWishSeq() {
		return wishSeq;
	}
	public void setWishSeq(String wishSeq) {
		this.wishSeq = wishSeq;
	}
}
