package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {

	private String prodSeq;
	private int prodType;
	private Integer prodUseFlag;
	private String prodName;
	private Integer feedSalaryAge;
	private Integer feedType;
	private Integer feedEtc;
	private Integer feedBrand;
	private Integer feedIngredient;
	private Integer feedSize;
	private Integer prodTeethFlag;
	private Integer prodTearsFlag;
	private Integer prodBrainFlag;
	private Integer prodImmunityFlag;
	private Integer prodBoneFlag;
	private Integer prodStressFlag;
	private Integer prodKidneyFlag;
	private Integer prodHeartFlag;
	private Integer prodAllergyFlag;
	private Integer prodPregnancyFlag;
	private Integer prodIntestineFlag;
	private Integer prodNeuteringFlag;
	private Integer prodWeightFlag;
	private Integer prodSkinFlag;
	private String prodRegDate;
	private String prodModDate;
	private int infoCount;
	
	private int funcCnt;
	
	private Integer infoPrice;
	private Double infoWeight;
	private Integer infoDiscount;
	private Integer infoDelFlag;
	private String product_prodSeq;
	
	List<Integer> feedPriceArray = new ArrayList<>();
	List<Double> feedWeightArray = new ArrayList<>();
	List<Integer> feedDiscountArray = new ArrayList<>();
	
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
	public Integer getProdUseFlag() {
		return prodUseFlag;
	}
	public void setProdUseFlag(Integer prodUseFlag) {
		this.prodUseFlag = prodUseFlag;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getFeedSalaryAge() {
		return feedSalaryAge;
	}
	public void setFeedSalaryAge(Integer feedSalaryAge) {
		this.feedSalaryAge = feedSalaryAge;
	}
	public Integer getFeedType() {
		return feedType;
	}
	public void setFeedType(Integer feedType) {
		this.feedType = feedType;
	}
	public Integer getProdTeethFlag() {
		return prodTeethFlag;
	}
	public void setProdTeethFlag(Integer prodTeethFlag) {
		this.prodTeethFlag = prodTeethFlag;
	}
	public Integer getProdTearsFlag() {
		return prodTearsFlag;
	}
	public void setProdTearsFlag(Integer prodTearsFlag) {
		this.prodTearsFlag = prodTearsFlag;
	}
	public Integer getProdBrainFlag() {
		return prodBrainFlag;
	}
	public void setProdBrainFlag(Integer prodBrainFlag) {
		this.prodBrainFlag = prodBrainFlag;
	}
	public Integer getProdImmunityFlag() {
		return prodImmunityFlag;
	}
	public void setProdImmunityFlag(Integer prodImmunityFlag) {
		this.prodImmunityFlag = prodImmunityFlag;
	}
	public Integer getProdBoneFlag() {
		return prodBoneFlag;
	}
	public void setProdBoneFlag(Integer prodBoneFlag) {
		this.prodBoneFlag = prodBoneFlag;
	}
	public Integer getProdStressFlag() {
		return prodStressFlag;
	}
	public void setProdStressFlag(Integer prodStressFlag) {
		this.prodStressFlag = prodStressFlag;
	}
	public Integer getProdKidneyFlag() {
		return prodKidneyFlag;
	}
	public void setProdKidneyFlag(Integer prodKidneyFlag) {
		this.prodKidneyFlag = prodKidneyFlag;
	}
	public Integer getProdHeartFlag() {
		return prodHeartFlag;
	}
	public void setProdHeartFlag(Integer prodHeartFlag) {
		this.prodHeartFlag = prodHeartFlag;
	}
	public Integer getProdAllergyFlag() {
		return prodAllergyFlag;
	}
	public void setProdAllergyFlag(Integer prodAllergyFlag) {
		this.prodAllergyFlag = prodAllergyFlag;
	}
	public Integer getProdPregnancyFlag() {
		return prodPregnancyFlag;
	}
	public void setProdPregnancyFlag(Integer prodPregnancyFlag) {
		this.prodPregnancyFlag = prodPregnancyFlag;
	}
	public Integer getProdIntestineFlag() {
		return prodIntestineFlag;
	}
	public void setProdIntestineFlag(Integer prodIntestineFlag) {
		this.prodIntestineFlag = prodIntestineFlag;
	}
	public Integer getProdNeuteringFlag() {
		return prodNeuteringFlag;
	}
	public void setProdNeuteringFlag(Integer prodNeuteringFlag) {
		this.prodNeuteringFlag = prodNeuteringFlag;
	}
	public Integer getProdWeightFlag() {
		return prodWeightFlag;
	}
	public void setProdWeightFlag(Integer prodWeightFlag) {
		this.prodWeightFlag = prodWeightFlag;
	}
	public Integer getProdSkinFlag() {
		return prodSkinFlag;
	}
	public void setProdSkinFlag(Integer prodSkinFlag) {
		this.prodSkinFlag = prodSkinFlag;
	}
	public Integer getFeedEtc() {
		return feedEtc;
	}
	public void setFeedEtc(Integer feedEtc) {
		this.feedEtc = feedEtc;
	}
	public Integer getFeedBrand() {
		return feedBrand;
	}
	public void setFeedBrand(Integer feedBrand) {
		this.feedBrand = feedBrand;
	}
	public Integer getFeedIngredient() {
		return feedIngredient;
	}
	public void setFeedIngredient(Integer feedIngredient) {
		this.feedIngredient = feedIngredient;
	}
	public Integer getFeedSize() {
		return feedSize;
	}
	public void setFeedSize(Integer feedSize) {
		this.feedSize = feedSize;
	}
	public String getProdRegDate() {
		return prodRegDate;
	}
	public void setProdRegDate(String prodRegDate) {
		this.prodRegDate = prodRegDate;
	}
	public String getProdModDate() {
		return prodModDate;
	}
	public void setProdModDate(String prodModDate) {
		this.prodModDate = prodModDate;
	}
	public int getFuncCnt() {
		return funcCnt;
	}
	public void setFuncCnt(int funcCnt) {
		this.funcCnt = funcCnt;
	}
	public int getInfoCount() {
		return infoCount;
	}
	public void setInfoCount(int infoCount) {
		this.infoCount = infoCount;
	}
	public Integer getInfoPrice() {
		return infoPrice;
	}
	public void setInfoPrice(Integer infoPrice) {
		this.infoPrice = infoPrice;
	}
	public Double getInfoWeight() {
		return infoWeight;
	}
	public void setInfoWeight(Double infoWeight) {
		this.infoWeight = infoWeight;
	}
	public Integer getInfoDiscount() {
		return infoDiscount;
	}
	public void setInfoDiscount(Integer infoDiscount) {
		this.infoDiscount = infoDiscount;
	}
	public Integer getInfoDelFlag() {
		return infoDelFlag;
	}
	public void setInfoDelFlag(Integer infoDelFlag) {
		this.infoDelFlag = infoDelFlag;
	}
	public String getProduct_prodSeq() {
		return product_prodSeq;
	}
	public void setProduct_prodSeq(String product_prodSeq) {
		this.product_prodSeq = product_prodSeq;
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
}
