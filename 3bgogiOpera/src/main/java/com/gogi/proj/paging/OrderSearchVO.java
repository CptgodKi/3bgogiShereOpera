package com.gogi.proj.paging;

import java.util.List;

public class OrderSearchVO {

	/*주문서 조회에 필요한
	필요 값 설정
	이후 OrdersVO로 통합이 가능하다면
	or이 붙은 값들은 전부 삭제 후 설정*/
	
	private String dateType;
	private int datePeriod;
	private String dateStart;
	private String dateEnd;
	private String searchType;
	private String searchKeyword;	
	private int reservationType;
	private int deliveryInvoiceFlag;
	private List<String> orSerialSpecialNumberList;
	private int minNum;
	private int maxNum;
	private String insertingCount;
	
	
	private int outputPosiv;				//출고여부
	private int refundFlag;					//환불 여부
	private int cancledFlag;				//취소 여부
	private int taxFlag;					//면세 여부
	private int specialRegionFlag;			//섬지역 여부 
	private int deliveryPrice;				//배송비 부담 주체
	private int productMatching;			//상품 매칭 여부
	private int specialReq;					//특별 요청 사항 여부
	private int delivMsgFlag;				//배송메세지여부
	private int totalQtyAlarm;				//송장 뽑을 때 총 합계 따로 표시 개수
	
	private String groupList;
	private String totalList;
	
	
	//추가사항
	private int ssPk; //스토어 고유값 넣어주기
	private List<String> ssPkList; //스토어 고유값
	private String ssList;
	
	private int matchingFlag; //매칭 여부
	
	//리스트 값은 쉼표로 나누기
	//다중 검색에 필요한 변수
	private List<String> searchKeywordList; //검색어 리스트값
	private List<Integer> searchKeywordNumList; //검색어 숫자 리스트값
	
	//제외 사항 변수
	private String exSearchType; //제외 검색어 타입
	private String exSerachKeyword; //제외 검색어
	private List<String> exSearchKeywordList; //제외 검색어 리스트값
	
	public int getTotalQtyAlarm() {
		return totalQtyAlarm;
	}

	public void setTotalQtyAlarm(int totalQtyAlarm) {
		this.totalQtyAlarm = totalQtyAlarm;
	}

	public String getSsList() {
		return ssList;
	}

	public void setSsList(String ssList) {
		this.ssList = ssList;
	}

	public List<String> getSsPkList() {
		return ssPkList;
	}

	public void setSsPkList(List<String> ssPkList) {
		this.ssPkList = ssPkList;
	}

	public int getDelivMsgFlag() {
		return delivMsgFlag;
	}

	public void setDelivMsgFlag(int delivMsgFlag) {
		this.delivMsgFlag = delivMsgFlag;
	}

	public int getSpecialReq() {
		return specialReq;
	}

	public void setSpecialReq(int specialReq) {
		this.specialReq = specialReq;
	}

	public String getGroupList() {
		return groupList;
	}

	public void setGroupList(String groupList) {
		this.groupList = groupList;
	}

	public String getTotalList() {
		return totalList;
	}

	public void setTotalList(String totalList) {
		this.totalList = totalList;
	}

	public int getMatchingFlag() {
		return matchingFlag;
	}

	public void setMatchingFlag(int matchingFlag) {
		this.matchingFlag = matchingFlag;
	}

	public int getOutputPosiv() {
		return outputPosiv;
	}

	public void setOutputPosiv(int outputPosiv) {
		this.outputPosiv = outputPosiv;
	}

	public int getRefundFlag() {
		return refundFlag;
	}

	public void setRefundFlag(int refundFlag) {
		this.refundFlag = refundFlag;
	}

	public int getCancledFlag() {
		return cancledFlag;
	}

	public void setCancledFlag(int cancledFlag) {
		this.cancledFlag = cancledFlag;
	}

	public int getTaxFlag() {
		return taxFlag;
	}

	public void setTaxFlag(int taxFlag) {
		this.taxFlag = taxFlag;
	}

	public int getSpecialRegionFlag() {
		return specialRegionFlag;
	}

	public void setSpecialRegionFlag(int specialRegionFlag) {
		this.specialRegionFlag = specialRegionFlag;
	}

	public int getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public int getProductMatching() {
		return productMatching;
	}

	public void setProductMatching(int productMatching) {
		this.productMatching = productMatching;
	}

	public String getInsertingCount() {
		return insertingCount;
	}

	public void setInsertingCount(String insertingCount) {
		this.insertingCount = insertingCount;
	}

	public int getMinNum() {
		return minNum;
	}

	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public List<Integer> getSearchKeywordNumList() {
		return searchKeywordNumList;
	}

	public void setSearchKeywordNumList(List<Integer> searchKeywordNumList) {
		this.searchKeywordNumList = searchKeywordNumList;
	}

	public List<String> getSearchKeywordList() {
		return searchKeywordList;
	}

	public void setSearchKeywordList(List<String> searchKeywordList) {
		this.searchKeywordList = searchKeywordList;
	}

	public String getExSearchType() {
		return exSearchType;
	}

	public void setExSearchType(String exSearchType) {
		this.exSearchType = exSearchType;
	}

	public String getExSerachKeyword() {
		return exSerachKeyword;
	}

	public void setExSerachKeyword(String exSerachKeyword) {
		this.exSerachKeyword = exSerachKeyword;
	}

	public List<String> getExSearchKeywordList() {
		return exSearchKeywordList;
	}

	public void setExSearchKeywordList(List<String> exSearchKeywordList) {
		this.exSearchKeywordList = exSearchKeywordList;
	}

	public int getSsPk() {
		return ssPk;
	}

	public void setSsPk(int ssPk) {
		this.ssPk = ssPk;
	}

	public List<String> getOrSerialSpecialNumberList() {
		return orSerialSpecialNumberList;
	}

	public void setOrSerialSpecialNumberList(List<String> orSerialSpecialNumberList) {
		this.orSerialSpecialNumberList = orSerialSpecialNumberList;
	}

	public int getDeliveryInvoiceFlag() {
		return deliveryInvoiceFlag;
	}

	public void setDeliveryInvoiceFlag(int deliveryInvoiceFlag) {
		this.deliveryInvoiceFlag = deliveryInvoiceFlag;
	}

	public int getReservationType() {
		return reservationType;
	}

	public void setReservationType(int reservationType) {
		this.reservationType = reservationType;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public int getDatePeriod() {
		return datePeriod;
	}

	public void setDatePeriod(int datePeriod) {
		this.datePeriod = datePeriod;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	/**
	 * Required Fields
	  	currentPage : 현재 페이지
		recordCountPerPage : 페이지당 보여질 레코드수
		blockSize : 블럭당 보여질 페이지 수
		totalRecord : totalRecord 총 레코드 수
	 * */
	
	private int currentPage = 1; //현재 페이지
	private int recordCountPerPage;  //pageSize 페이지당 보여질 레코드수
	private int blockSize; //블럭당 보여질 페이지 수
	private int totalRecord; //총 레코드 수
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
	public int getBlockSize() {
		return blockSize;
	}
	
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	/**
	 * Not Required Fields
	 * 
	 */	
	private int totalPage;  //총 페이지수
	private int firstPage;  //블럭당 시작 페이지, 1, 11, 21, 31, ...
	private int lastPage; //블럭당 마지막 페이지 10, 20, 30, 40, ...
	private int firstRecordIndex; //페이지당 시작 인덱스 0, 5, 10, 15 ...
	private int lastRecordIndex;  //페이지당 마지막 인덱스	5,10,15,20....
	  
	public int getTotalPage() {
		totalPage=(int)Math.ceil((float)totalRecord/recordCountPerPage);
		//totalPage = ((getTotalRecord()-1)/getRecordCountPerPage()) + 1;
		
		return totalPage;
	}
		
	public int getFirstPage() {
		firstPage= currentPage-((currentPage-1)%blockSize);
		//firstPage = ((getCurrentPage()-1)/getBlockSize())*getBlockSize() + 1;
		return firstPage;
	}
	
	public int getLastPage() {		
		lastPage = firstPage+(blockSize-1);
		//lastPage = getFirstPage() + getBlockSize() - 1;		
		if(lastPage > getTotalPage()){
			lastPage = getTotalPage();
		}
		return lastPage;
	}

	public int getFirstRecordIndex() {
		//curPos=(currentPage-1)*pageSize;
		firstRecordIndex = (getCurrentPage() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}

	public int getLastRecordIndex() {
		lastRecordIndex = getCurrentPage() * getRecordCountPerPage();
		return lastRecordIndex;
	}
	
    
}