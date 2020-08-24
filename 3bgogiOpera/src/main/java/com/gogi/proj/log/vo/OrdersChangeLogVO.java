package com.gogi.proj.log.vo;

import java.sql.Timestamp;

public class OrdersChangeLogVO {

	private int oclPk; // 주문서 변동 고유번호
	private int orFk; // 주문서 고유 번호
	private String oclDetail; //수정 사항
	private String oclAdmin; //수정자
	private String oclReason; // 수정이유
	private Timestamp oclRegdate; //수정일
	
	public OrdersChangeLogVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersChangeLogVO(int oclPk, int orFk, String oclDetail, String oclAdmin, String oclReason,
			Timestamp oclRegdate) {
		super();
		this.oclPk = oclPk;
		this.orFk = orFk;
		this.oclDetail = oclDetail;
		this.oclAdmin = oclAdmin;
		this.oclReason = oclReason;
		this.oclRegdate = oclRegdate;
	}

	public int getOclPk() {
		return oclPk;
	}

	public void setOclPk(int oclPk) {
		this.oclPk = oclPk;
	}

	public int getOrFk() {
		return orFk;
	}

	public void setOrFk(int orFk) {
		this.orFk = orFk;
	}

	public String getOclDetail() {
		return oclDetail;
	}

	public void setOclDetail(String oclDetail) {
		this.oclDetail = oclDetail;
	}

	public String getOclAdmin() {
		return oclAdmin;
	}

	public void setOclAdmin(String oclAdmin) {
		this.oclAdmin = oclAdmin;
	}

	public String getOclReason() {
		return oclReason;
	}

	public void setOclReason(String oclReason) {
		this.oclReason = oclReason;
	}

	public Timestamp getOclRegdate() {
		return oclRegdate;
	}

	public void setOclRegdate(Timestamp oclRegdate) {
		this.oclRegdate = oclRegdate;
	}

	@Override
	public String toString() {
		return "OrdersChangeLogVO [oclPk=" + oclPk + ", orFk=" + orFk + ", oclDetail=" + oclDetail + ", oclAdmin="
				+ oclAdmin + ", oclReason=" + oclReason + ", oclRegdate=" + oclRegdate + "]";
	}
	
}
