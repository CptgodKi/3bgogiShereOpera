package com.gogi.proj.epost.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

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
	 * @MethodName : deliveryPrintTarget
	 * @date : 2020. 9. 9.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 송장 부여 및 업데이트하기
	 */
	public OrdersVO deliveryPrintTarget(OrderSearchVO osVO, String ip, String adminId);
	
	
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
	 * @메소드설명 : 발송일을 기준으로 송장번호를 가져와 우체국에 접수가 되었는지 여부 확인
	 */
	public List<OrdersVO> selectDeliveryInvoiceNumberByDate(OrderSearchVO osVO) throws IOException, ParseException;
}
