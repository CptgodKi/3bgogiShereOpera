package com.gogi.proj.epost.model;

import java.util.List;

import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface EpostDAO {

	public List<RegDataVO> selectEpostSendingData(OrderSearchVO osVO);
	
	public int grantRegiNoByOrPk(RegDataVO regData);
	
	public RegDataVO selectEpostInfoByOrserialspecialnumber(String orSerialSpecialNumber);
	
	public int deleteDelivInfo(String orSerialSpecialNumber);
	
	public List<OrdersVO> selectDontGrantDelivOrderListInMonth(OrderSearchVO osVO);
	
	public int selectDontGrantDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : selectSelfprintTest
	 * @date : 2020. 9. 4.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 :	 테스트용
	 */
	public List<OrdersVO> selectSelfprintTest();
}
