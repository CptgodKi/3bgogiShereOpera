package com.gogi.proj.classification.code.vo;

public class ExcelOrderSeqVO {

	private int eosPk; //주문서 위치 고유 번호
	private int eosSeq; //주문서 순서
	private String eosLocation; //담당 주문 위치 명
	private String eosCode; //담당 위치 고유 코드
	
	public ExcelOrderSeqVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExcelOrderSeqVO(int eosPk, int eosSeq, String eosLocation, String eosCode) {
		super();
		this.eosPk = eosPk;
		this.eosSeq = eosSeq;
		this.eosLocation = eosLocation;
		this.eosCode = eosCode;
	}

	public int getEosPk() {
		return eosPk;
	}

	public void setEosPk(int eosPk) {
		this.eosPk = eosPk;
	}

	public int getEosSeq() {
		return eosSeq;
	}

	public void setEosSeq(int eosSeq) {
		this.eosSeq = eosSeq;
	}

	public String getEosLocation() {
		return eosLocation;
	}

	public void setEosLocation(String eosLocation) {
		this.eosLocation = eosLocation;
	}

	public String getEosCode() {
		return eosCode;
	}

	public void setEosCode(String eosCode) {
		this.eosCode = eosCode;
	}

	@Override
	public String toString() {
		return "ExcelOrderSeqVO [eosPk=" + eosPk + ", eosSeq=" + eosSeq + ", eosLocation=" + eosLocation + ", eosCode="
				+ eosCode + "]";
	}

}
