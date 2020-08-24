package com.gogi.proj.analytics.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Service
public class AnalyticsServiceImpl implements AnalyticsService{

	@Autowired
	private AnalyticsDAO analyDAO;
	
	@Override
	public List<OrdersVO> sevendaysTotalSalesWithoutCommision(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.sevendaysTotalSalesWithoutCommision(osVO);
	}

	@Override
	public List<OrdersVO> sevendaysTotalSales(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.sevendaysTotalSales(osVO);
	}

	@Override
	public List<OrdersVO> sevendaysSendingOut(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.sevendaysSendingOut(osVO);
	}

	@Override
	public List<OrdersVO> sixMonthTotalSales(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.sixMonthTotalSales(osVO);
	}

	@Override
	public List<OrdersVO> selectSevenDaysOutPutProductQty() {
		// TODO Auto-generated method stub
		return analyDAO.selectSevenDaysOutPutProductQty();
	}

	@Override
	public List<Map<String, Object>> selectTodayDeliveryCount() {
		// TODO Auto-generated method stub
		return analyDAO.selectTodayDeliveryCount();
	}

	@Override
	public List<Map<String, Object>> selectMainDeliveryResult() {
		// TODO Auto-generated method stub
		return analyDAO.selectMainDeliveryResult();
	}

	@Override
	public List<Map<String, Object>> selectPriceChartInsert() {
		// TODO Auto-generated method stub
		return analyDAO.selectPriceChartInsert();
	}

	@Override
	public List<Map<String, Object>> selectAnalyDataList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return analyDAO.selectAnalyDataList(osVO);
	}

}
