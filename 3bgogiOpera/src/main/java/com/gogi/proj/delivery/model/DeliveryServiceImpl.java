package com.gogi.proj.delivery.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Service
public class DeliveryServiceImpl implements DeliveryService{

	@Autowired
	private DeliveryDAO deliDao;

	@Override
	public List<OrdersVO> selectDelivTargetByOrDeliveryInvoiceNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return deliDao.selectDelivTargetByOrDeliveryInvoiceNumber(orVO);
	}

	@Override
	@Transactional
	public int updateOrderSendingDay(List<String> orPkList) {
		// TODO Auto-generated method stub
		int result = 0;
		OrdersVO orVO;
		
		Timestamp now = new Timestamp(new Date().getTime());
		
		for(int i=0; i<orPkList.size(); i++) {
			orVO = new OrdersVO();
			orVO.setOrSendingDay(now);
			orVO.setOrPk(Integer.parseInt(orPkList.get(i)));
			
			result += deliDao.updateOrderSendingDay(orVO);
		}
		
		return result;
	}

	@Override
	@Transactional
	public int updateDeliveryOutPutOrder(List<Integer> orPkList) {
		// TODO Auto-generated method stub
		
		OrdersVO orVO = new OrdersVO();
		Timestamp sendingTime = new Timestamp(new Date().getTime());
		
		int result = 0;
		
		for(int i = 0; i < orPkList.size(); i++) {
			orVO.setOrPk(orPkList.get(i));
			orVO.setOrOutputDate(sendingTime);
			result += deliDao.updateDeliveryOutPutOrder(orVO);
		}
		
		return result;
	}

	@Override
	@Transactional
	public int updateDeliveryOutPutCancled(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		
		int result = deliDao.updateDeliveryOutPutCancled(osVO);
		return result;
	}

	@Override
	public List<OrdersVO> selectStoreSendingResultByDate(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return deliDao.selectStoreSendingResultByDate(osVO);
	}

	@Override
	@Transactional
	public int storeSendingFinished(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return deliDao.storeSendingFinished(osVO);
	}

	@Override
	public List<Map<String, Object>> selectSendingExcel(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return deliDao.selectSendingExcel(ssVO);
	}
	
}
