package com.gogi.proj.analytics.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class AnalyticsDAOMybatis extends SqlSessionDaoSupport implements AnalyticsDAO {

	private String namespace = "analytics.customer";
	
	private String productNameSpace = "analytics.product";
	
	private String mainNameSpace = "analytics.main";

	@Override
	public List<OrdersVO> sevendaysTotalSalesWithoutCommision(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sevendaysTotalSalesWithoutCommision",osVO);
	}

	@Override
	public List<OrdersVO> sevendaysTotalSales(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sevendaysTotalSales",osVO);
	}

	@Override
	public List<OrdersVO> sevendaysSendingOut(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sevendaysSendingOut",osVO);
	}

	@Override
	public List<OrdersVO> sixMonthTotalSales(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".sixMonthTotalSales", osVO);
	}

	@Override
	public List<OrdersVO> selectSevenDaysOutPutProductQty() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectSevenDaysOutPutProductQty");
	}

	@Override
	public List<Map<String, Object>> selectTodayDeliveryCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(productNameSpace+".selectTodayDeliveryCount");
	}

	@Override
	public List<Map<String, Object>> selectMainDeliveryResult() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(mainNameSpace+".selectMainDeliveryResult");
	}

	@Override
	public List<Map<String, Object>> selectPriceChartInsert() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(mainNameSpace+".selectPriceChartInsert");
	}

	@Override
	public List<Map<String, Object>> selectAnalyDataList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectAnalyDataList", osVO);
	}
	
}
