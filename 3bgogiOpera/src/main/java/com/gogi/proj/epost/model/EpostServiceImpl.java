package com.gogi.proj.epost.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.epost.api.EpostSendingUtil;
import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.EmptyCheckUtil;

@Service
public class EpostServiceImpl implements EpostService {

	@Autowired
	private EpostDAO epostDao;

	private static final Logger logger = LoggerFactory.getLogger(EpostServiceImpl.class);
	
	@Override
	public List<RegDataVO> selectEpostSendingData(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		
		return epostDao.selectEpostSendingData(osVO);
	}

	@Override
	public int grantRegiNoByOrPk(RegDataVO regVO, RegDataVO reVO, boolean updateType) {
		
		if(reVO.getError_code() != null) return 0;
		
		if(updateType == true) {			
			String [] orPkList = regVO.getOrPk().split(",");
			int result = 0;
			for(int i=0; i < orPkList.length; i++) {			
				reVO.setOrPk(orPkList[i]);
				//regVO.setRegiNo(regiNo);
				result = epostDao.grantRegiNoByOrPk(reVO);
				
				if(EmptyCheckUtil.isEmpty(reVO.getRegiNo())) return 0;
			}
			
			return result;
		}else {
			
			return 1;
		}
	}

	@Transactional
	@Override
	public String deleteEpostDelivData(List<String> orSerialSpecialNumberList, String epostUrl) throws Exception {
		// TODO Auto-generated method stub
		EpostSendingUtil esu = new EpostSendingUtil();
		
		int result = 0 ;
		int errorResult = 0;
		
		StringBuilder results = new StringBuilder("");
		
		RegDataVO regVO;
		String regData = "";
		String encryptStr = "";
		
		for(int i=0; i < orSerialSpecialNumberList.size(); i++) {
			regVO = epostDao.selectEpostInfoByOrserialspecialnumber(orSerialSpecialNumberList.get(i));
			
			//직접 입력된 송장일 경우 송장 정보가 없음
			if(regVO == null) {
				epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
				logger.info("송장 삭제, regVO 정보가 존재하지 않아 직접 입력된 것으로 판단, 데이터베이스 상으로 삭제처리");
			}else {				
					try {
						regData = regVO.epostDeliteToString();
						
						encryptStr = esu.epostEncrypting(regData);
						RegDataVO sendingResult = esu.epostSending(encryptStr, epostUrl);
						
						if(sendingResult.getCanceledyn() != null) {				
							if(sendingResult.getCanceledyn().equals("Y")) {
								epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
								result++;
								
							}else if(sendingResult.getNotcancelReason() != null) {
								results.append(sendingResult.getNotcancelReason()+"<br>");
								epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
							}else {
								results.append(""+orSerialSpecialNumberList.get(i)+" => "+sendingResult.getMessage()+"<br>");
							}
						}else {
							epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
							result++;
						}
						
					}catch(NullPointerException e) {
						regData = "";
						epostDao.deleteDelivInfo(orSerialSpecialNumberList.get(i));
						errorResult++;
					}
			}
			
		}
		
		results.append("<br>삭제 완료된 개수 = "+result+" 장");
		
		return results.toString();
	}

	@Override
	public List<OrdersVO> selectDontGrantDelivOrderListInMonth(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return epostDao.selectDontGrantDelivOrderListInMonth(osVO);
	}

	@Override
	public int selectDontGrantDelivOrderListInMonthCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return epostDao.selectDontGrantDelivOrderListInMonthCounting(osVO);
	}
}
