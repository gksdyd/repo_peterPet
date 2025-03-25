package com.peterpet.demo.module.product;

public class FeedInfoVo extends ProductVo {

	private String infoSeq;
	private Integer price;
	private Double weight;
	private Integer discount;

	public String getSeq() {
		return infoSeq;
	}

	public void setSeq(String seq) {
		this.infoSeq = seq;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	public void setFeedInfo(FeedInfoVo vo) {
		if (getRegisterFlag() == 0) {
			feedInfoArr.clear();
		}
		
		// 기능 추가
		if (getAddOrRemoveFlag() == 1) {
			feedInfoArr.add(vo);
		// 기능 제거
		} else if (getAddOrRemoveFlag() == -1) {
			for (int i = 0; i < feedInfoArr.size(); i++) {
				if (feedInfoArr.get(i).getSeq() == (vo.getSeq())) {
					feedInfoArr.remove(i);
					break;
				}
			}
		}
	}
}
