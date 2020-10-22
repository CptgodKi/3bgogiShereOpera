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
	 * @MethodName : deliveryPrintTarget
	 * @date : 2020. 9. 9.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 자체 프린트 타겟
	 */
	public OrdersVO deliveryPrintTarget(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : grantDeliveryInvoiceNumber
	 * @date : 2020. 9. 9.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 부여된 송장 정보 입력
	 */
	public int grantDeliveryInvoiceNumber(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : deliveryReprinting
	 * @date : 2020. 9. 25.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 송장 재출력
	 */
	public OrdersVO deliveryInvoiceNumberReprinting(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : selectDeliveryInvoiceNumberByDate
	 * @date : 2020. 9. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 발송기한을 기준으로 주문자,수령자,주소,송장번호 가져오기
	 */
	public List<OrdersVO> selectDeliveryInvoiceNumberByDate(OrderSearchVO osVO);
}
