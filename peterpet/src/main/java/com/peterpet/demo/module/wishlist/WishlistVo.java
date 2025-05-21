package com.peterpet.demo.module.wishlist;

import java.util.ArrayList;

import com.peterpet.demo.module.base.BaseVo;

public class WishlistVo extends BaseVo {

	private String userSeq;
	private String prodSeq;
	private String wishSeq;
	private ArrayList<Integer> cart;
	
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
	public ArrayList<Integer> getCart() {
		return cart;
	}
	public void setCart(ArrayList<Integer> cart) {
		this.cart = cart;
	}
}
