package com.gogi.proj.epost.model;

import java.util.List;

import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface EpostService {

	public List<RegDataVO> selectEpostSendingData(OrderSearchVO osVO);
	
	public int grantRegiNoByOrPk(RegDataVO regVO, RegDataVO rdVO, boolean updateType);
	
	public String deleteEpostDelivData(List<String> orSerialSpecialNumberList, String epostUrl) throws Exception;
	
	public List<OrdersVO> selectDontGrantDelivOrderListInMonth(OrderSearchVO osVO);
	
	public int selectDontGrantDelivOrderListInMonthCounting(OrderSearchVO osVO);
}
