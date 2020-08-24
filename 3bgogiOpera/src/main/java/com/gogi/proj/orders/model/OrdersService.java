package com.gogi.proj.orders.model;

import java.util.List;

import com.gogi.proj.configurations.vo.StoreMergeVO;
import com.gogi.proj.orders.vo.IrregularOrderVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;

public interface OrdersService {
	
	/*고객 필터링*/
	
	//고객 필터링 추가
	public int addIrregularOrders(IrregularOrderVO iroVO);
	
	//필터링 되지 않은 대기상태 상태 값 목록
	public List<IrregularOrderVO> selectIrregularOrdersNotFiltered();
	
	//모든 고객필터링 값 가져오기
	public List<IrregularOrderVO> selectIrregularOrders();
	
	//필터링 확인처리 하기
	public int successedFiltering(IrregularOrderVO iroVO);
	
	//필터링 확인사항 수정하기
	public int updateFilteringData(IrregularOrderVO iroVO);
	
	//필터링 데이터 삭제하기
	public int deleteFilteringData(IrregularOrderVO iroVO);
	
	/*고객 필터링 끝*/
	/*cs 시작*/
	
	//주문고객 검색하기
	public List<OrdersVO> searchCustomerOrderInfo(OrderSearchVO osVO);
	
	public OrdersVO selectOrdersByPk(int orPk);
	
	//주문고객 총 수 검색
	public int searchCustomerOrderInfoCounting(OrderSearchVO osVO);
	
	//주문서의 상품 상세사항 검색
	public List<OrdersVO> selectCustomerOrderProductInfoDetail(OrdersVO orVO);
	
	//주문서 삭제처리하기
	public boolean deleteOrders(List<String> orSerialSpecialNumberList);
	
	public boolean devideOrders(int [] orPkList);
	
	//단일 주문서 삭제 처리하기
	public boolean deleteOrdersOne(OrdersVO orVO);
	
	//주문서 나누기
	public boolean selfDevideOrders(int orPk, int orderDevideType, int radioDevideValue, int selfDevideOriginalValue, int selfDevideValue) throws CloneNotSupportedException;
	
	//고유번호로 합포할 주문서 목록 가져오기
	public List<OrdersVO> selectCombineInfoBySerialSpecialNumber(List<String> orSerialSpecialNumber);
	
	public boolean updateCombineOrders(List<String> orSerialSpecialNumber, OrdersVO combineOrderData);
	
	public int changeProductAndOptionByOrPk(OrdersVO orVO);
	
	public int addProductAndOptionIntoOrders(List<String> orSerialSpecialNumberList, OrdersVO insertOrdersVO);
	/*cs 끝*/

	public int[] insertOrderData(List<OrdersVO> orderList, int ssPk);
	
	public List<OrdersVO> selectTotalOrderInToday();
	
	public int deleteOrders(int [] orPk);
	
	public List<OrdersVO> selectOrderByOrOrderNumber(OrdersVO ordersVO);
	
	public List<OrdersVO> selectNotMatchingedOrders(OrderSearchVO orderSearchVO);
	
	public int countingNotMatchingedOrders(OrderSearchVO orderSearchVO);
	
	//배송중 데이터로 업데이트하기
	public int updateOrderDeliveryInvoiceNumber(List<OrdersVO> orList);
	
	//임시 주문서 뽑기
	public List<OrdersVO> selectedOrderExcelByOrderSerachVO(OrderSearchVO osVO);
	
	/*오늘 들어온 날짜로 주문건, 입력일 조회하기*/
	public List<OrdersVO> selectOrdersCountingByInputDate();
	
	/*입력일로 주문서를 조회하여 일괄 삭제하기*/
	public int deleteOrdersByDate(OrdersVO ordersVO);
	
	//발송 취소하기
	public int outputCancledBySerialNumber(OrdersVOList orVOList);
	
	//발송일 변경
	public int changeSendingDeadline(OrderSearchVO osVO);
	
	
	/**
	 * 
	 * @MethodName : updateOutputDateBySerialNumber
	 * @date : 2020. 7. 14.
	 * @author : Jeon KiChan
	 * @param orVOList
	 * @return
	 * @메소드설명 : 발송 처리하기 임의로 orSerialSpecialNumber에 string값 날짜를 집어넣어 업데이트함
	 */
	public int updateOutputDateBySerialNumber(OrdersVOList orVOList);
	
	
	/**
	 * 
	 * @MethodName : selectedOrderExcelByOrderSerachVOForVegit
	 * @date : 2020. 7. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 야채 한사람당으로 목록 가져오기
	 */
	public List<OrdersVOList> selectedOrderExcelByOrderSerachVOForVegit(OrderSearchVO osVO);
	
	
	
	/**
	 * 
	 * @MethodName : devideOneOrderInManyProducts
	 * @date : 2020. 7. 16.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 한 상품 안에 하나 이상의 물품이 존재할 경우 개별 주문서로 분리 기능
	 */
	public int devideOneOrderInManyProducts(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : searchRefundOrder
	 * @date : 2020. 7. 22.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 환불 처리에 필요한 정보 가져오기
	 */
	public OrdersVO searchRefundOrder(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : orderRefundsEdit
	 * @date : 2020. 7. 22.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 데이터상 환불 처리하기
	 */
	public int orderRefundsEdit(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : addCustomerSpecialRequest
	 * @date : 2020. 7. 27.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 고객요청사항 저장하기
	 */
	public int addCustomerSpecialRequest(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : selectCustomerSpecialRequest
	 * @date : 2020. 7. 27.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 고객요청사항 불러오기
	 */
	public OrdersVO selectCustomerSpecialRequest(OrdersVO orVO);
	
	/**
	 * 
	 * @MethodName : selectDeliveryMsg
	 * @date : 2020. 7. 29.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 배송메세지 일괄 확인하기 ( 완전 매칭된 주문서만 확인가능 )
	 */
	public List<OrdersVO> selectDeliveryMsg(OrderSearchVO osVO);

	
	/**
	 * 
	 * @MethodName : editDelivNum
	 * @date : 2020. 7. 30.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 묶음 고유 값으로 송장번호 업데이트하기
	 */
	public int editDelivNum(OrdersVO orVO);
	
	
	
	/**
	 * 
	 * @MethodName : selectOrderQtyByPk
	 * @date : 2020. 8. 3.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 고유값으로 주문서의 상품 수량 가져오기
	 */
	public OrdersVO selectOrderQtyByPk(OrdersVO orVO);
	
	
	/**
	 * 
	 * @MethodName : multiMatchingDevide
	 * @date : 2020. 8. 12.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @return
	 * @메소드설명 : 복수 매칭된 주문서 각각의 상품으로 나눠주기
	 */
	public String multiMatchingDevide(OrdersVO orVO);
}