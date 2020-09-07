package com.gogi.proj.epost.model;

import java.util.List;

import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface EpostService {

	public List<RegDataVO> selectEpostSendingData(OrderSearchVO osVO);
	
	public int grantRegiNoByOrPk(RegDataVO regVO, RegDataVO rdVO, boolean updateType);
	
	public String deleteEpostDelivData(List<String> orSerialSpecialNumberList, String epostUrl) throws Exception;
	
	public List<OrdersVO> selectDontGrantDelivOrderListInMonth(OrderSearchVO osVO);
	
	public int selectDontGrantDelivOrderListInMonthCounting(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : selectSelfprintTest
	 * @date : 2020. 9. 4.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 테스트
	 */
	public List<OrdersVO> selectSelfprintTest();
	
	
	/**
	 * 
	 * @MethodName : grantDeliveryNumber
	 * @date : 2020. 9. 7.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 웹에서 직접 송장을 프린트 할 때 송장부여하기
	 */
	public int grantDeliveryNumber(OrdersVO orVO);
}
