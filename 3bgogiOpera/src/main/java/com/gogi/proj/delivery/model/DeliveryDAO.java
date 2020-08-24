package com.gogi.proj.delivery.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

public interface DeliveryDAO {

	
	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(OrdersVO orVO);
	
	/**
	 * 
	 * @MethodName : updateOrderSendingDay
	 * @date : 2020. 3. 16.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 발송처리하기
	 */
	public int updateOrderSendingDay(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : updateDeliveryOutPutOrder
	 * @date : 2020. 3. 16.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 :스토어 발송처리 기능, 한 번만 발송처리 가능
	 */
	public int updateDeliveryOutPutOrder(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : updateDeliveryOutPutCancled
	 * @date : 2020. 3. 16.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 스토어 발송처리 취소 기능
	 */
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
	
	
	/**
	 * 
	 * @MethodName : storeSendingFinished
	 * @date : 2020. 6. 2.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 :  스토어 미발송 주문 건 발송 처리
	 */
	public int storeSendingFinished(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectSendingExcel
	 * @date : 2020. 6. 3.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @return
	 * @메소드설명 : 스토어 발송엑셀파일 데이터 가져오기
	 */
	public List<Map<String, Object>> selectSendingExcel(StoreSectionVO ssVO);
}
