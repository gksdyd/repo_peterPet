package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

import com.peterpet.demo.module.base.BaseVo;
import com.peterpet.demo.module.base.Constants;

public class ProductVo extends BaseVo{

	private static int currProdType = Constants.INIT_PRODUCT_TYPE;
	public List<String> prodFuncSeqArray = new ArrayList<>();
	public List<String> prodFuncArray = new ArrayList<>();
	public List<String> prodFuncNameArray = new ArrayList<>();
	
	List<String> feedInfoSeqArray = new ArrayList<>();
	List<Integer> feedPriceArray = new ArrayList<>();
	List<Double> feedWeightArray = new ArrayList<>();
	List<Integer> feedDiscountArray = new ArrayList<>();
	
	private String prodSeq;
	private int prodType;
	private int prodPetType;
	
	private Integer prodFunction;	// 선택한 기능을 저장하기 위한 매개체
	
	private Integer shFeedSalaryAge;
	private Integer shFeedType;
	private Integer shFeedEtc;
	private Integer shFeedSize;
	private Integer shFeedBrand;
	private Integer shFeedIngredient;
	
	private Integer shSnackType;
	private Integer shSnackSalaryAge;
	private Integer shSnackMaterial;
	private Integer shSnackBrand;
	private Integer shSnackIngredient;
	
	private Integer gridMethod = 0;
	private Integer shSortBy = 0;
	private Integer shFeedWeight;
	
	private List<String> badgeArray = new ArrayList<>();
	
	public List<String> getProdFuncArray() {
		return prodFuncArray;
	}

	public void setProdFuncArray(List<String> prodFuncArray) {
		this.prodFuncArray = prodFuncArray;
	}

	public List<String> getProdFuncNameArray() {
		return prodFuncNameArray;
	}

	public void setProdFuncNameArray(List<String> prodFuncNameArray) {
		this.prodFuncNameArray = prodFuncNameArray;
	}

	public String getProdSeq() {
		return prodSeq;
	}

	public void setProdSeq(String prodSeq) {
		this.prodSeq = prodSeq;
	}

	public int getProdType() {
		return prodType;
	}

	public void setProdType(int prodType) {
		this.prodType = prodType;
	}
	
	public static int getCurrProdType() {
		return currProdType;
	}

	public static void setCurrProdType(int currProdType) {
		ProductVo.currProdType = currProdType;
	}

	public Integer getProdFunction() {
		return prodFunction;
	}

	public void setProdFunction(Integer prodFunction) {
		this.prodFunction = prodFunction;
	}
	
	public Integer getShFeedSalaryAge() {
		return shFeedSalaryAge;
	}

	public void setShFeedSalaryAge(Integer shFeedSalaryAge) {
		this.shFeedSalaryAge = shFeedSalaryAge;
	}

	public Integer getShFeedType() {
		return shFeedType;
	}

	public void setShFeedType(Integer shFeedType) {
		this.shFeedType = shFeedType;
	}

	public Integer getShFeedEtc() {
		return shFeedEtc;
	}

	public void setShFeedEtc(Integer shFeedEtc) {
		this.shFeedEtc = shFeedEtc;
	}

	public Integer getShFeedSize() {
		return shFeedSize;
	}

	public void setShFeedSize(Integer shFeedSize) {
		this.shFeedSize = shFeedSize;
	}

	public Integer getShFeedBrand() {
		return shFeedBrand;
	}

	public void setShFeedBrand(Integer shFeedBrand) {
		this.shFeedBrand = shFeedBrand;
	}

	public Integer getShFeedIngredient() {
		return shFeedIngredient;
	}

	public void setShFeedIngredient(Integer shFeedIngredient) {
		this.shFeedIngredient = shFeedIngredient;
	}

	public Integer getShSnackType() {
		return shSnackType;
	}

	public void setShSnackType(Integer shSnackType) {
		this.shSnackType = shSnackType;
	}

	public Integer getShSnackSalaryAge() {
		return shSnackSalaryAge;
	}

	public void setShSnackSalaryAge(Integer shSnackSalaryAge) {
		this.shSnackSalaryAge = shSnackSalaryAge;
	}

	public Integer getShSnackMaterial() {
		return shSnackMaterial;
	}

	public void setShSnackMaterial(Integer shSnackMaterial) {
		this.shSnackMaterial = shSnackMaterial;
	}

	public Integer getShSnackBrand() {
		return shSnackBrand;
	}

	public void setShSnackBrand(Integer shSnackBrand) {
		this.shSnackBrand = shSnackBrand;
	}

	public Integer getShSnackIngredient() {
		return shSnackIngredient;
	}

	public void setShSnackIngredient(Integer shSnackIngredient) {
		this.shSnackIngredient = shSnackIngredient;
	}
	
	public List<Integer> getFeedPriceArray() {
		return feedPriceArray;
	}

	public void setFeedPriceArray(List<Integer> feedPriceArray) {
		this.feedPriceArray = feedPriceArray;
	}

	public List<Double> getFeedWeightArray() {
		return feedWeightArray;
	}

	public void setFeedWeightArray(List<Double> feedWeightArray) {
		this.feedWeightArray = feedWeightArray;
	}

	public List<Integer> getFeedDiscountArray() {
		return feedDiscountArray;
	}

	public void setFeedDiscountArray(List<Integer> feedDiscountArray) {
		this.feedDiscountArray = feedDiscountArray;
	}

	public List<String> getProdFuncSeqArray() {
		return prodFuncSeqArray;
	}

	public void setProdFuncSeqArray(List<String> prodFuncSeqArray) {
		this.prodFuncSeqArray = prodFuncSeqArray;
	}

	public List<String> getFeedInfoSeqArray() {
		return feedInfoSeqArray;
	}

	public void setFeedInfoSeqArray(List<String> feedInfoSeqArray) {
		this.feedInfoSeqArray = feedInfoSeqArray;
	}

	public Integer getGridMethod() {
		return gridMethod;
	}

	public void setGridMethod(Integer gridMethod) {
		this.gridMethod = gridMethod;
	}

	public Integer getShSortBy() {
		return shSortBy;
	}

	public void setShSortBy(Integer shSortBy) {
		this.shSortBy = shSortBy;
	}

	public int getProdPetType() {
		return prodPetType;
	}

	public void setProdPetType(int prodPetType) {
		this.prodPetType = prodPetType;
	}

	public List<String> getBadgeArray() {
		return badgeArray;
	}

	public void setBadgeArray(List<String> badgeArray) {
		this.badgeArray = badgeArray;
	}

	public Integer getShFeedWeight() {
		return shFeedWeight;
	}

	public void setShFeedWeight(Integer shFeedWeight) {
		this.shFeedWeight = shFeedWeight;
	}

	public void InitProdType() {
		if (getProdType() != currProdType) {
			if (getProdType() == 0) {
				setProdType(currProdType);
			} else {
				currProdType = getProdType();				
			}
		}
	}
}
