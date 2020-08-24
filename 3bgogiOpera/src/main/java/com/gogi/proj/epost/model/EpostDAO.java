package com.gogi.proj.epost.model;

import java.util.List;

import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface EpostDAO {

	public List<RegDataVO> selectEpostSendingData(OrderSearchVO osVO);
	
	public int grantRegiNoByOrPk(RegDataVO regData);
	
	public RegDataVO selectEpostInfoByOrserialspecialnumber(String orSerialSpecialNumber);
	
	public int deleteDelivInfo(String orSerialSpecialNumber);
	
	public List<OrdersVO> selectDontGrantDelivOrderListInMonth(OrderSearchVO osVO);
	
	public int selectDontGrantDelivOrderListInMonthCounting(OrderSearchVO osVO);
}
