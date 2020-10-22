package com.gogi.proj.log.model;

import java.util.List;

import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.vo.OrdersVO;

public interface LogDAO {

	
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
	 * @MethodName : selectOrderPkBySerialSpecialNumber
	 * @date : 2020. 10. 7.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 주문서 분리번호로 주문서 고유값가져오기
	 */
	public List<OrderHistoryVO> selectOrderPkBySerialSpecialNumber(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : selectOrdersChangeBeforeValueByOrPk
	 * @date : 2020. 10. 7.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 주문서 고유값으로 변경이전 값 조회해서 가져오기 
	 */
	public OrdersVO selectOrdersChangeBeforeValueByOrPk(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : selectOrdersChangeBeforeValueBySerialSpecialNumber
	 * @date : 2020. 10. 7.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 주문서 분리번호로 변경이전 값 조회해서 가져오기
	 */
	public List<OrdersVO> selectOrdersChangeBeforeValueBySerialSpecialNumber(OrdersVO orVO);
	
}
