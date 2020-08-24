package com.gogi.proj.classification.code.vo;

public class CostCodeVO {
	
	private int ccPk; //원가 분류 고유 번호
	private String ccCode; //원가 코드
	private String ccCodeType; // 원가 코드 타입-명
	
	public CostCodeVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CostCodeVO(int ccPk, String ccCode, String ccCodeType) {
		super();
		this.ccPk = ccPk;
		this.ccCode = ccCode;
		this.ccCodeType = ccCodeType;
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

	@Override
	public String toString() {
		return "CostCodeVO [ccPk=" + ccPk + ", ccCode=" + ccCode + ", ccCodeType=" + ccCodeType + "]";
	}
	
}
