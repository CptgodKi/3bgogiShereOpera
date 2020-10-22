package com.gogi.proj.epost.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.epost.api.EpostSendingUtil;
import com.gogi.proj.epost.controller.EpostController;
import com.gogi.proj.epost.vo.RegDataVO;
import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.util.EmptyCheckUtil;
import com.gogi.proj.util.naverMapApiUtil;

@Service
public class EpostServiceImpl implements EpostService {

	@Autowired
	private EpostDAO epostDao;
	
	@Autowired
	private LogService logService;

	private static final Logger logger = LoggerFactory.getLogger(EpostServiceImpl.class);
	
	static final String EPOST_DELIV_SENDING = "http://ship.epost.go.kr/api.InsertOrder.jparcel";
	
	static final String EPOST_DELIV_SENDING_VER_2 = "http://ship.epost.go.kr/api.InsertOrder.jparcel";
	//송장삭제
	static final String EPOST_DELIV_DELETE = "http://ship.epost.go.kr/api.GetResCancelCmd.jparcel";
	
	private static EpostSendingUtil esu = new EpostSendingUtil();
	
	
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

	@Transactional
	@Override
	public OrdersVO deliveryPrintTarget(OrderSearchVO osVO, String ip, String adminId) {
		
		OrdersVO orderList = epostDao.deliveryPrintTarget(osVO);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		
		String dates = sdf.format(today);
		
		OrdersVO resOr = null;
		OrderHistoryVO ohVO = null;
		
			try {
				
				//System.out.println(orList.epostDelivSelfPrintToString());
				//sendingData = esu.epostSending( esu.epostEncrypting(orList.epostDelivSelfPrintToString()) , EpostServiceImpl.EPOST_DELIV_SENDING);
				
				resOr = (OrdersVO)esu.selfPrintepostSendingTest(esu.epostEncrypting(orderList.epostDelivSelfPrintToString()), "http://ship.epost.go.kr/api.InsertOrder.jparcel", orderList);

				if(resOr.getRegiNo() != null) {
					
					
					/*String [] orPkSplit = resOr.getOrUserColumn1().split(",");
					System.out.println(orPkSplit.toString());
					List<String> orProductList = new ArrayList<String>();

					for(int j=0; j < orPkSplit.length ; j++) {
						System.out.println("orPkSplit[j] = > "+orPkSplit[j]);
						orProductList.add(orPkSplit[j]);
					}
					
					resOr.setOrProductList(orProductList);*/
					
					int result = epostDao.grantDeliveryInvoiceNumber(resOr);
					
					for(int i = 0; i < resOr.getProductOptionList().size(); i++) {
						
						ohVO = new OrderHistoryVO();
						ohVO.setOrFk(resOr.getProductOptionList().get(i).getAnotherOptionPk());
						ohVO.setOhIp(ip);
						ohVO.setOhAdmin(adminId);
						ohVO.setOhRegdate(dates);
						ohVO.setOhEndPoint("송장 생성");
						ohVO.setOhDetail("송장 생성 완료 => 우체국 ( "+resOr.getRegiNo()+" )");
						
						logService.insertOrderHistory(ohVO);
					}
					
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return resOr;
	}

	@Override
	public OrdersVO deliveryInvoiceNumberReprinting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return epostDao.deliveryInvoiceNumberReprinting(osVO);
	}

	@Override
	public List<OrdersVO> selectDeliveryInvoiceNumberByDate(OrderSearchVO osVO) throws IOException, ParseException {
		// TODO Auto-generated method stub
		List<OrdersVO> orList = epostDao.selectDeliveryInvoiceNumberByDate(osVO);
		
		List<Map<String, Object>> deliveryInfoList = new ArrayList<>();
		EpostSendingUtil esu = new EpostSendingUtil();
		naverMapApiUtil nmu = new naverMapApiUtil();
		
		for(OrdersVO orVO : orList) {
			
			String urlParameters = "regkey="+esu.getEpost_api_key()
	                +"&target=trace&query="+orVO.getOrDeliveryInvoiceNumber()+"&regymd="+osVO.getDateStart().replaceAll("-", "");
			
	        URL obj = new URL("http://biz.epost.go.kr/KpostPortal/openapi");
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        //add reuqest header
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Connection", "keep-alive");
	        con.setRequestProperty("Host", "biz.epost.go.kr");
	        con.setRequestProperty("User-Agent", "Apache-HttpClient/4.5.1(Java/1.8.0_91)");
	        // Send post request
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(urlParameters);
	        wr.flush();
	        wr.close();

	        int responseCode = con.getResponseCode();

	        Charset charset = Charset.forName("UTF-8");
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),charset));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        
	        JSONObject xmlJSONObj = XML.toJSONObject(response.toString());
			String objString = xmlJSONObj.toString();
	       
			Map<String, Object> result = (Map<String, Object>) naverMapApiUtil.returnJson(objString).get("trace");
	        
	        if(result != null) {
	        	orVO.setMessage("발송 완료");
	        }else {
	        	Map<String, Object> xs = (Map<String, Object>) naverMapApiUtil.returnJson(objString).get("xsync");
	        	if(xs != null) {
	        		
	        		Map<String, Object> x1 = (Map<String, Object>) xs.get("xsyncData");
	        		orVO.setMessage((String)x1.get("message"));
	        		
	        	}else {
	        		Map<String, Object> error = (Map<String, Object>) naverMapApiUtil.returnJson(objString).get("error");
	        		orVO.setMessage((String)error.get("message"));
	        	}
	        }
	        
	        //String msg = (String)naverMapApiUtil.returnJson(objString).get("xsync");
	        
	        //orVO.setMessage(naverMapApiUtil.returnJson(objString));
	        
	        in.close();
		}
		
		return orList;
	}

}
