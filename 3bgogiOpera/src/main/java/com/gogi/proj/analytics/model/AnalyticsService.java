package com.gogi.proj.analytics.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.another.vo.DatesVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

public interface AnalyticsService {

	//OrderSearchVO를 parameter로 둬서 후에 재사용이 가능하도록 함
		//7일간 매출 수수료 계산 x
		public List<OrdersVO> sevendaysTotalSalesWithoutCommision(OrderSearchVO osVO);
		
		//7일간 매출 수수료 계산, 
		public List<OrdersVO> sevendaysTotalSales(OrderSearchVO osVO);
		
		//7일간 발송 건 수
		public List<OrdersVO> sevendaysSendingOut(OrderSearchVO osVO);
		
		//최근 6개월 간 매출
		public List<OrdersVO> sixMonthTotalSales(OrderSearchVO osVO);
		
		//최근 7일간 물품 보낸 개수
		public List<OrdersVO> selectSevenDaysOutPutProductQty();
		
		public List<Map<String, Object>> selectTodayDeliveryCount();
		
		public List<Map<String, Object>> selectMainDeliveryResult();
		
		//최근 한달간의 가격대별 주문 개수 확인하기 
		public List<Map<String, Object>> selectPriceChartInsert();
		
		//통계 테스트
		public List<Map<String, Object>> selectAnalyDataList(OrderSearchVO osVO);
		
		/**
		 * 
		 * @MethodName : selectReservProductQty
		 * @date : 2020. 9. 9.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 예약된 물품 개수 보기
		 */
		public List<Map<String, Object>> selectReservProductQty(OrderSearchVO osVO);
		
		
		public List<Map<String, Object>> selectReservProductQtyInMonth(DatesVO datesVO);
		
		
		/**
		 * 
		 * @MethodName : selectTotalSalesByDates
		 * @date : 2020. 10. 13.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 매출 조회하기
		 */
		public List<OrdersVO> selectTotalSalesByDates(OrderSearchVO osVO);
		
		
		/**
		 * 
		 * @MethodName : selectCancledSalesByDates
		 * @date : 2020. 10. 13.
		 * @author : Jeon KiChan
		 * @param osVO
		 * @return
		 * @메소드설명 : 취소 건 조회하기
		 */
		public List<OrdersVO> selectCancledSalesByDates(OrderSearchVO osVO);
}
