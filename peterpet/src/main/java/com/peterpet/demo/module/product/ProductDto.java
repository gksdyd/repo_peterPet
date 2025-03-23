package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

public class ProductDto {

	private String prodSeq;
	private int prodType;
	private int prodUseFlag;
	private String prodName;
	private int feedSalaryAge;
	private int feedType;
	private int funcTeethFlag;
	private int funcTearsFlag;
	private int funcBrainFlag;
	private int funcImmunityFlag;
	private int funcBoneFlag;
	private int funcStressFlag;
	private int funcKidneyFlag;
	private int funcHeartFlag;
	private int funcAllergyFlag;
	private int funcPregnancyFlag;
	private int funcIntestineFlag;
	private int funcNeuteringFlag;
	private int funcWeightFlag;
	private int funcSkinFlag;
	private int feedEtc;
	private int feedBrand;
	private int feedIngredient;
	private int feedSize;
	private String prodRegDate;
	private String prodModDate;
	private int feedFunction;	// 선택한 기능을 저장하기 위한 매개체
	private int registerFlag;	// 등록 진행 여부 플래그
	private int addOrRemoveFlag;	// 뱃지 추가 또는 제거 플래그
	
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
	public int getProdUseFlag() {
		return prodUseFlag;
	}
	public void setProdUseFlag(int prodUseFlag) {
		this.prodUseFlag = prodUseFlag;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getFeedSalaryAge() {
		return feedSalaryAge;
	}
	public void setFeedSalaryAge(int feedSalaryAge) {
		this.feedSalaryAge = feedSalaryAge;
	}
	public int getFeedType() {
		return feedType;
	}
	public void setFeedType(int feedType) {
		this.feedType = feedType;
	}
	public int getFuncTeethFlag() {
		return funcTeethFlag;
	}
	public void setFuncTeethFlag(int funcTeethFlag) {
		this.funcTeethFlag = funcTeethFlag;
	}
	public int getFuncTearsFlag() {
		return funcTearsFlag;
	}
	public void setFuncTearsFlag(int funcTearsFlag) {
		this.funcTearsFlag = funcTearsFlag;
	}
	public int getFuncBrainFlag() {
		return funcBrainFlag;
	}
	public void setFuncBrainFlag(int funcBrainFlag) {
		this.funcBrainFlag = funcBrainFlag;
	}
	public int getFuncImmunityFlag() {
		return funcImmunityFlag;
	}
	public void setFuncImmunityFlag(int funcImmunityFlag) {
		this.funcImmunityFlag = funcImmunityFlag;
	}
	public int getFuncBoneFlag() {
		return funcBoneFlag;
	}
	public void setFuncBoneFlag(int funcBoneFlag) {
		this.funcBoneFlag = funcBoneFlag;
	}
	public int getFuncStressFlag() {
		return funcStressFlag;
	}
	public void setFuncStressFlag(int funcStressFlag) {
		this.funcStressFlag = funcStressFlag;
	}
	public int getFuncKidneyFlag() {
		return funcKidneyFlag;
	}
	public void setFuncKidneyFlag(int funcKidneyFlag) {
		this.funcKidneyFlag = funcKidneyFlag;
	}
	public int getFuncHeartFlag() {
		return funcHeartFlag;
	}
	public void setFuncHeartFlag(int funcHeartFlag) {
		this.funcHeartFlag = funcHeartFlag;
	}
	public int getFuncAllergyFlag() {
		return funcAllergyFlag;
	}
	public void setFuncAllergyFlag(int funcAllergyFlag) {
		this.funcAllergyFlag = funcAllergyFlag;
	}
	public int getFuncPregnancyFlag() {
		return funcPregnancyFlag;
	}
	public void setFuncPregnancyFlag(int funcPregnancyFlag) {
		this.funcPregnancyFlag = funcPregnancyFlag;
	}
	public int getFuncIntestineFlag() {
		return funcIntestineFlag;
	}
	public void setFuncIntestineFlag(int funcIntestineFlag) {
		this.funcIntestineFlag = funcIntestineFlag;
	}
	public int getFuncNeuteringFlag() {
		return funcNeuteringFlag;
	}
	public void setFuncNeuteringFlag(int funcNeuteringFlag) {
		this.funcNeuteringFlag = funcNeuteringFlag;
	}
	public int getFuncWeightFlag() {
		return funcWeightFlag;
	}
	public void setFuncWeightFlag(int funcWeightFlag) {
		this.funcWeightFlag = funcWeightFlag;
	}
	public int getFuncSkinFlag() {
		return funcSkinFlag;
	}
	public void setFuncSkinFlag(int funcSkinFlag) {
		this.funcSkinFlag = funcSkinFlag;
	}
	public int getFeedEtc() {
		return feedEtc;
	}
	public void setFeedEtc(int feedEtc) {
		this.feedEtc = feedEtc;
	}
	public int getFeedBrand() {
		return feedBrand;
	}
	public void setFeedBrand(int feedBrand) {
		this.feedBrand = feedBrand;
	}
	public int getFeedIngredient() {
		return feedIngredient;
	}
	public void setFeedIngredient(int feedIngredient) {
		this.feedIngredient = feedIngredient;
	}
	public int getFeedSize() {
		return feedSize;
	}
	public void setFeedSize(int feedSize) {
		this.feedSize = feedSize;
	}
	public int getFeedFunction() {
		return feedFunction;
	}
	public void setFeedFunction(int feedFunction) {
		this.feedFunction = feedFunction;
	}
	public int getRegisterFlag() {
		return registerFlag;
	}
	public void setRegisterFlag(int registerFlag) {
		this.registerFlag = registerFlag;
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
	public int getAddOrRemoveFlag() {
		return addOrRemoveFlag;
	}
	public void setAddOrRemoveFlag(int addOrRemoveFlag) {
		this.addOrRemoveFlag = addOrRemoveFlag;
	}

	public static List<Integer> feedFuncArr = new ArrayList<>();
	
	public void InitProdType() {
		if (getProdType() == 0) {
			setProdType(1);
		}
	}
	
	public void feedFuncInit() {
		// 등록 중이 아니라면 클리어
		if (getRegisterFlag() == 0) {
			feedFuncArr.clear();
		}
		
		// 기능 추가
		if (addOrRemoveFlag == 1) {
			if (feedFuncArr.size() == 0) {
				feedFuncArr.add(getFeedFunction());
			} else {
				for (int i = 0; i < feedFuncArr.size(); i++) {
					if (feedFuncArr.get(i) == getFeedFunction()) {
						break;
					}
					if (i == feedFuncArr.size() - 1) {
						feedFuncArr.add(getFeedFunction());
					}
				}
			}
		// 기능 제거
		} else if (addOrRemoveFlag == -1) {
			for (int i = 0; i < feedFuncArr.size(); i++) {
				if (feedFuncArr.get(i) == getFeedFunction()) {
					feedFuncArr.remove(i);
					break;
				}
			}
		}
		
		// 기능 존재하는 플래그 서치 및 활성화
		for (int i = 0; i < feedFuncArr.size(); i++) {
			System.out.println(feedFuncArr.get(i));
			if (feedFuncArr.get(i) == 20) {
				setFuncTeethFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 21) {
				setFuncTearsFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 22) {
				setFuncBrainFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 23) {
				setFuncImmunityFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 24) {
				setFuncBoneFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 25) {
				setFuncStressFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 26) {
				setFuncKidneyFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 27) {
				setFuncHeartFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 28) {
				setFuncAllergyFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 29) {
				setFuncPregnancyFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 30) {
				setFuncIntestineFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 31) {
				setFuncNeuteringFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 32) {
				setFuncWeightFlag(feedFuncArr.get(i));
			} else if (feedFuncArr.get(i) == 33) {
				setFuncSkinFlag(feedFuncArr.get(i));
			}
		}
	}
}
