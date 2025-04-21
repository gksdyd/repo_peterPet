package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {

	private String prodSeq;
	private int prodType;
	private Integer prodUseFlag;
	private String prodName;
	private String prodScore;
	private Integer feedSalaryAge;
	private Integer feedType;
	private Integer feedEtc;
	private Integer feedBrand;
	private Integer feedIngredient;
	private Integer feedSize;
	private Integer prodPetType;
	private Integer prodIsStock;
	private String prodRegDate;
	private String prodModDate;
	private int infoCount;
	private int funcCount;
	private int reviewCount;
	
	private String infoSeq;
	private Integer infoPrice;
	private Double infoWeight;
	private Integer infoDiscount;
	private Integer infoDelFlag;
	private int infoMain = 0;
	private String product_prodSeq;
	
	private String funcSeq;
	private String funcName;
	private int funcMain = 0;
	
	private int discountPrice;
	
	public List<String> prodFuncSeqArray = new ArrayList<>();
	public List<String> prodFuncArray = new ArrayList<>();
	
	List<String> feedInfoSeqArray = new ArrayList<>();
	List<Integer> feedPriceArray = new ArrayList<>();
	List<Double> feedWeightArray = new ArrayList<>();
	List<Integer> feedDiscountArray = new ArrayList<>();
	
	private String weightArr;
	String[] weightArray; 
	
	private Integer scoreOne;
	private Integer scoreTwo;
	private Integer scoreThree;
	private Integer scoreFour;
	private Integer scoreFive;
	
	private String reviSeq;
	private Integer reviScore;
	private String reviText;
	private String reviRegDate;
	private String reviModDate;
	private Integer reviGood;
	private String userId;
	private String petName;
	private Integer petVarieties;
	private String petBirth;
	private Integer petWeight;
	
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
	public int getFuncCount() {
		return funcCount;
	}
	public void setFuncCount(int funcCount) {
		this.funcCount = funcCount;
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
	public String getFuncSeq() {
		return funcSeq;
	}
	public void setFuncSeq(String funcSeq) {
		this.funcSeq = funcSeq;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public List<String> getProdFuncArray() {
		return prodFuncArray;
	}
	public void setProdFuncArray(List<String> prodFuncArray) {
		this.prodFuncArray = prodFuncArray;
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
	public String getInfoSeq() {
		return infoSeq;
	}
	public void setInfoSeq(String infoSeq) {
		this.infoSeq = infoSeq;
	}
	public int getInfoMain() {
		return infoMain;
	}
	public void setInfoMain(int infoMain) {
		this.infoMain = infoMain;
	}
	public int getFuncMain() {
		return funcMain;
	}
	public void setFuncMain(int funcMain) {
		this.funcMain = funcMain;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	public String getProdScore() {
		return prodScore;
	}
	public void setProdScore(String prodScore) {
		this.prodScore = prodScore;
	}
	public void calculatePrice() {
		discountPrice = infoPrice * (100 - infoDiscount) / 100;
	}
	public Integer getProdPetType() {
		return prodPetType;
	}
	public void setProdPetType(Integer prodPetType) {
		this.prodPetType = prodPetType;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public String getWeightArr() {
		return weightArr;
	}
	public void setWeightArr(String weightArr) {
		this.weightArr = weightArr;
	}
	public String[] getWeightArray() {
		return weightArray;
	}
	public void setWeightArray(String[] weightArray) {
		this.weightArray = weightArray;
	}
	public Integer getProdIsStock() {
		return prodIsStock;
	}
	public void setProdIsStock(Integer prodIsStock) {
		this.prodIsStock = prodIsStock;
	}
	public Integer getScoreOne() {
		return scoreOne;
	}
	public void setScoreOne(Integer scoreOne) {
		this.scoreOne = scoreOne;
	}
	public Integer getScoreTwo() {
		return scoreTwo;
	}
	public void setScoreTwo(Integer scoreTwo) {
		this.scoreTwo = scoreTwo;
	}
	public Integer getScoreThree() {
		return scoreThree;
	}
	public void setScoreThree(Integer scoreThree) {
		this.scoreThree = scoreThree;
	}
	public Integer getScoreFour() {
		return scoreFour;
	}
	public void setScoreFour(Integer scoreFour) {
		this.scoreFour = scoreFour;
	}
	public Integer getScoreFive() {
		return scoreFive;
	}
	public void setScoreFive(Integer scoreFive) {
		this.scoreFive = scoreFive;
	}
	public String getReviSeq() {
		return reviSeq;
	}
	public void setReviSeq(String reviSeq) {
		this.reviSeq = reviSeq;
	}
	public Integer getReviScore() {
		return reviScore;
	}
	public void setReviScore(Integer reviScore) {
		this.reviScore = reviScore;
	}
	public String getReviText() {
		return reviText;
	}
	public void setReviText(String reviText) {
		this.reviText = reviText;
	}
	public String getReviRegDate() {
		return reviRegDate;
	}
	public void setReviRegDate(String reviRegDate) {
		this.reviRegDate = reviRegDate;
	}
	public String getReviModDate() {
		return reviModDate;
	}
	public void setReviModDate(String reviModDate) {
		this.reviModDate = reviModDate;
	}
	public Integer getReviGood() {
		return reviGood;
	}
	public void setReviGood(Integer reviGood) {
		this.reviGood = reviGood;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public Integer getPetVarieties() {
		return petVarieties;
	}
	public void setPetVarieties(Integer petVarieties) {
		this.petVarieties = petVarieties;
	}
	public String getPetBirth() {
		return petBirth;
	}
	public void setPetBirth(String petBirth) {
		this.petBirth = petBirth;
	}
	public Integer getPetWeight() {
		return petWeight;
	}
	public void setPetWeight(Integer petWeight) {
		this.petWeight = petWeight;
	}
}
