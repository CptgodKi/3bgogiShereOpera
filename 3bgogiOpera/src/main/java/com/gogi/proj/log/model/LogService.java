package com.gogi.proj.log.model;

import java.util.List;

import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.vo.OrdersVO;

public interface LogService {

	
	/**
	 * 
	 * @MethodName : changeOrderHistory
	 * @date : 2020. 10. 7.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 주문서 기록 ( cs에서 변경 이력이 많은 경우 사용 ) 
	 */
	public int changeOrderHistory(OrdersVO orVO, boolean orPkExist, String accessIp, String adminId, String accessPage, String regdate);
	
	
	/**
	 * 
	 * @MethodName : selectOrderHistoryByOrPk
	 * @date : 2020. 10. 7.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 주문서 고유값으로 기록 내역 가져오기
	 */
	public List<OrderHistoryVO> selectOrderHistoryByOrPk(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : insertOrderHistory
	 * @date : 2020. 10. 7.
	 * @author : Jeon KiChan
	 * @param ohVO
	 * @return
	 * @메소드설명 : 주문서 기록 
	 */
	public int insertOrderHistory(OrderHistoryVO ohVO);
	
}
