package com.gogi.proj.classification.code.vo;

import java.util.List;

public class CostCodeVO {
	
	private int ccPk; //원가 분류 고유 번호
	private String ccCode; //원가 코드
	private String ccCodeType; // 원가 코드 타입-명
	private boolean ccCarcassFlag; // 부분육 입고 여부
	private List<CostCodeVO> ccList;

	public CostCodeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CostCodeVO(int ccPk, String ccCode, String ccCodeType, boolean ccCarcassFlag, List<CostCodeVO> ccList) {
		super();
		this.ccPk = ccPk;
		this.ccCode = ccCode;
		this.ccCodeType = ccCodeType;
		this.ccCarcassFlag = ccCarcassFlag;
		this.ccList = ccList;
	}

	public int getCcPk() {
		return ccPk;
	}

	public void setCcPk(int ccPk) {
		this.ccPk = ccPk;
	}

	public String getCcCode() {
		return ccCode;
	}

	public void setCcCode(String ccCode) {
		this.ccCode = ccCode;
	}

	public String getCcCodeType() {
		return ccCodeType;
	}

	public void setCcCodeType(String ccCodeType) {
		this.ccCodeType = ccCodeType;
	}

	public boolean isCcCarcassFlag() {
		return ccCarcassFlag;
	}

	public void setCcCarcassFlag(boolean ccCarcassFlag) {
		this.ccCarcassFlag = ccCarcassFlag;
	}

	public List<CostCodeVO> getCcList() {
		return ccList;
	}

	public void setCcList(List<CostCodeVO> ccList) {
		this.ccList = ccList;
	}

	@Override
	public String toString() {
		return "CostCodeVO [ccPk=" + ccPk + ", ccCode=" + ccCode + ", ccCodeType=" + ccCodeType + ", ccCarcassFlag="
				+ ccCarcassFlag + ", ccList=" + ccList + "]";
	}
	
}
