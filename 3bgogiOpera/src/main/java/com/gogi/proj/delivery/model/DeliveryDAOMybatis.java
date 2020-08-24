package com.gogi.proj.delivery.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Repository
public class DeliveryDAOMybatis extends SqlSessionDaoSupport implements DeliveryDAO{

	private String namespace = "delivery";

	@Override
	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectDelivTargetByOrDeliveryInvoiceNumber",orVO);
	}

	@Override
	public int updateOrderSendingDay(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateOrderSendingDay", orVO);
	}

	@Override
	public int updateDeliveryOutPutOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateDeliveryOutPutOrder", orVO);
	}

	@Override
	public int updateDeliveryOutPutCancled(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateDeliveryOutPutCancled", osVO);
	}

	@Override
	public List<OrdersVO> selectStoreSendingResultByDate(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectStoreSendingResultByDate", osVO);
	}

	@Override
	public int storeSendingFinished(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".storeSendingFinished", osVO);
	}
	
	@Override
	public List<Map<String, Object>> selectSendingExcel(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectSendingExcel", ssVO);
	}
}
