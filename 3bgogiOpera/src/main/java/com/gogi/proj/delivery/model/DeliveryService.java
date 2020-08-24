package com.gogi.proj.delivery.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface DeliveryService {

	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(OrdersVO orVO);
	
	public int updateOrderSendingDay(List<String> orPkList);
	
	public int updateDeliveryOutPutOrder(List<Integer> orPkList);
	
	public int updateDeliveryOutPutCancled(OrderSearchVO osVO);
	
	/**
	 * 
	 * @MethodName : selectStoreSendingResultByDate
	 * @date : 2020. 6. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 스토어 발송 처리 목록 가져오기
	 */
	public List<OrdersVO> selectStoreSendingResultByDate(OrderSearchVO osVO);
	
	public int storeSendingFinished(OrderSearchVO osVO);
	
	public List<Map<String, Object>> selectSendingExcel(StoreSectionVO ssVO); 
	
}
