package com.peterpet.demo.module.pet;

import java.util.ArrayList;
import java.util.List;

import com.peterpet.demo.module.base.BaseDto;

public class PetDto extends BaseDto {

	private String petSeq;
	private Integer petType;
	private Integer petVarieties;
	private Integer petGender;
	private String petName;
	private Integer petNickname;
	private String petBirth;
	private Double petWeight;
	private Integer petVaccinationFlag;
	private Integer petNeuteringFlag;
	private String user_userSeq;
	private String petRegDate;
	private String petModDate;
	private String userSeq;
	private String userName;
	private int personalCount;
	private int diseaseCount;
	
	private int petDelFlag;
	
	private String persSeq;
	private Integer persDiscription;
	private String diseSeq;
	private Integer diseDiscription;
	
	private List<Integer> petPersonalArray = new ArrayList<>();
	private List<Integer> petDiseaseArray = new ArrayList<>();
	
	private int petAge;
	
	public String getPetSeq() {
		return petSeq;
	}
	public void setPetSeq(String petSeq) {
		this.petSeq = petSeq;
	}
	public Integer getPetVarieties() {
		return petVarieties;
	}
	public void setPetVarieties(Integer petVarieties) {
		this.petVarieties = petVarieties;
	}
	public Integer getPetGender() {
		return petGender;
	}
	public void setPetGender(Integer petGender) {
		this.petGender = petGender;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public Integer getPetNickname() {
		return petNickname;
	}
	public void setPetNickname(Integer petNickname) {
		this.petNickname = petNickname;
	}
	public String getPetBirth() {
		return petBirth;
	}
	public void setPetBirth(String petBirth) {
		this.petBirth = petBirth;
	}
	public Double getPetWeight() {
		return petWeight;
	}
	public void setPetWeight(Double petWeight) {
		this.petWeight = petWeight;
	}
	public Integer getPetVaccinationFlag() {
		return petVaccinationFlag;
	}
	public void setPetVaccinationFlag(Integer petVaccinationFlag) {
		this.petVaccinationFlag = petVaccinationFlag;
	}
	public Integer getPetNeuteringFlag() {
		return petNeuteringFlag;
	}
	public void setPetNeuteringFlag(Integer petNeuteringFlag) {
		this.petNeuteringFlag = petNeuteringFlag;
	}
	public String getUser_userSeq() {
		return user_userSeq;
	}
	public void setUser_userSeq(String user_userSeq) {
		this.user_userSeq = user_userSeq;
	}
	public String getPetRegDate() {
		return petRegDate;
	}
	public void setPetRegDate(String petRegDate) {
		this.petRegDate = petRegDate;
	}
	public String getPetModDate() {
		return petModDate;
	}
	public void setPetModDate(String petModDate) {
		this.petModDate = petModDate;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPetDelFlag() {
		return petDelFlag;
	}
	public void setPetDelFlag(int petDelFlag) {
		this.petDelFlag = petDelFlag;
	}
	public int getPersonalCount() {
		return personalCount;
	}
	public void setPersonalCount(int personalCount) {
		this.personalCount = personalCount;
	}
	public int getDiseaseCount() {
		return diseaseCount;
	}
	public void setDiseaseCount(int diseaseCount) {
		this.diseaseCount = diseaseCount;
	}
	public Integer getPersDiscription() {
		return persDiscription;
	}
	public void setPersDiscription(Integer persDiscription) {
		this.persDiscription = persDiscription;
	}
	public Integer getDiseDiscription() {
		return diseDiscription;
	}
	public void setDiseDiscription(Integer diseDiscription) {
		this.diseDiscription = diseDiscription;
	}
	public int getPetAge() {
		return petAge;
	}
	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}
	public Integer getPetType() {
		return petType;
	}
	public void setPetType(Integer petType) {
		this.petType = petType;
	}
	public List<Integer> getPetPersonalArray() {
		return petPersonalArray;
	}
	public void setPetPersonalArray(List<Integer> petPersonalArray) {
		this.petPersonalArray = petPersonalArray;
	}
	public List<Integer> getPetDiseaseArray() {
		return petDiseaseArray;
	}
	public void setPetDiseaseArray(List<Integer> petDiseaseArray) {
		this.petDiseaseArray = petDiseaseArray;
	}
	public String getPersSeq() {
		return persSeq;
	}
	public void setPersSeq(String persSeq) {
		this.persSeq = persSeq;
	}
	public String getDiseSeq() {
		return diseSeq;
	}
	public void setDiseSeq(String diseSeq) {
		this.diseSeq = diseSeq;
	}
}
