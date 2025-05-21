package com.peterpet.demo.module.wishlist;

import java.util.ArrayList;

import com.peterpet.demo.module.base.BaseVo;

public class WishlistVo extends BaseVo {

	private String userSeq;
	private String prodSeq;
	private String wishSeq;
	private ArrayList<Integer> products;
	private ArrayList<Integer> counts;
	private ArrayList<Double> weights;
	private ArrayList<Integer> prices;
	
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
	public ArrayList<Integer> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Integer> products) {
		this.products = products;
	}
	public ArrayList<Integer> getCounts() {
		return counts;
	}
	public void setCounts(ArrayList<Integer> counts) {
		this.counts = counts;
	}
	public ArrayList<Double> getWeights() {
		return weights;
	}
	public void setWeights(ArrayList<Double> weights) {
		this.weights = weights;
	}
	public ArrayList<Integer> getPrices() {
		return prices;
	}
	public void setPrices(ArrayList<Integer> prices) {
		this.prices = prices;
	}
}
