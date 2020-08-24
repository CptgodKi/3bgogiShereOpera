package com.gogi.proj.orders.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gogi.proj.configurations.model.ConfigurationDAO;
import com.gogi.proj.configurations.vo.StoreMergeVO;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.config.model.StoreExcelDataSortingDAO;
import com.gogi.proj.orders.util.OrderUtilityClass;
import com.gogi.proj.orders.vo.IrregularOrderVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.stock.model.StockService;
import com.gogi.proj.util.StringToListUtil;

@Service
public class OrdersServiceImpl implements OrdersService{

	private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private ConfigurationDAO configDAO;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private StoreExcelDataSortingDAO sedsDao;
	
	@Transactional
	public int [] insertOrderData(List<OrdersVO> orderList, int ssPk) {
		int [] counts = new int[3];
		int count = 0;
		int dupliCount = 0;
		int mergedSuccessedResult = 0;
		
		if(ssPk == 4) {
			OrdersVO temp = null;
			
			int counting = 0;
			
			for(OrdersVO ori : orderList) {
				
				if(counting == 0) {
					try {
						temp = ori.copy();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					counting++;
					
				}else if(!ori.getOrBuyerName().equals("")) {
					try {
						temp = ori.copy();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else if(ori.getOrBuyerName().equals("")) {
					
					ori.setOrBuyerName(temp.getOrBuyerName());
					ori.setOrSettlementDay(temp.getOrSettlementDay());
					ori.setOrDeliveryPrice(temp.getOrDeliveryPrice());
					ori.setOrBuyerId(temp.getOrBuyerId());
					ori.setOrBuyerName(temp.getOrBuyerName());
					ori.setOrBuyerContractNumber1(temp.getOrBuyerContractNumber1());
					ori.setOrReceiverName(temp.getOrReceiverName());
					ori.setOrReceiverContractNumber1(temp.getOrReceiverContractNumber1());
					ori.setOrShippingAddressNumber(temp.getOrShippingAddressNumber());
					ori.setOrShippingAddress(temp.getOrShippingAddress());
					ori.setOrDeliveryMessage(temp.getOrDeliveryMessage());
					
					try {
						temp = ori.copy();
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					counting++;
				}
				
				
			}
		}
		
		for(OrdersVO vo : orderList) {
			int result = ordersDAO.insertOrderData(vo);
			if(result > 0) count++;
			else if(result == 0) dupliCount++;
		}
		
		StoreSectionVO ssVO =  configDAO.selectStoreSectionBySspk(ssPk);
		
		int specialNumber = ssVO.getSsSpecialNumberCount();
		
		
		List<OrdersVO> NotMergedList = ordersDAO.selectNotMergedOrders(ssVO);
		
		for(int i=0; i < NotMergedList.size(); i++) {
			NotMergedList.get(i).setOrSerialSpecialNumber(ssVO.getSsStoreNickname()+"-"+specialNumber);
			NotMergedList.get(i).setOrMergeList(StringToListUtil.makeForeach(NotMergedList.get(i).getOrMerge()));
			ordersDAO.grantOrSerialSpecialNumber(NotMergedList.get(i));
			
			specialNumber++;
			mergedSuccessedResult++;
		}
		
		ssVO.setSsSpecialNumberCount(specialNumber);
		
		configDAO.increaseStoreSectionSpecialNumber(ssVO);
		
		counts[0] = count;
		counts[1] = dupliCount;
		counts[2] = mergedSuccessedResult;
		
		return counts;
	}

	@Override
	public List<OrdersVO> selectTotalOrderInToday() {
		// TODO Auto-generated method stub
		return ordersDAO.selectTotalOrderInToday();
	}

	@Override
	@Transactional
	public int deleteOrders(int[] orPk) {
		// TODO Auto-generated method stub
		int result = 0;
		OrdersVO orVO = new OrdersVO();
		for(int i = 0; i < orPk.length; i++) {
			if(ordersDAO.deleteOrders(orPk[i]) > 0) {
				orVO.setOrPk(orPk[i]);
				result++;
				
				boolean stockResult = stockService.updateProductChangeValues(orVO);
				if(stockResult == false) {
					result--;
				}
			}
		}
		
		return result;
	}

	@Override
	public List<OrdersVO> selectOrderByOrOrderNumber(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return ordersDAO.selectOrderByOrOrderNumber(ordersVO);
	}

	@Override
	public List<OrdersVO> selectNotMatchingedOrders(OrderSearchVO orderSearchVO) {
		// TODO Auto-generated method stub
		return ordersDAO.selectNotMatchingedOrders(orderSearchVO);
	}

	@Override
	public int countingNotMatchingedOrders(OrderSearchVO orderSearchVO) {
		// TODO Auto-generated method stub
		return ordersDAO.countingNotMatchingedOrders(orderSearchVO);
	}

	/*고객 필터링 시작*/
	@Override
	public int addIrregularOrders(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return ordersDAO.addIrregularOrders(iroVO);
	}

	@Override
	public List<IrregularOrderVO> selectIrregularOrdersNotFiltered() {
		// TODO Auto-generated method stub
		return ordersDAO.selectIrregularOrdersNotFiltered();
	}

	@Override
	public List<IrregularOrderVO> selectIrregularOrders() {
		// TODO Auto-generated method stub
		return ordersDAO.selectIrregularOrders();
	}

	@Override
	public int successedFiltering(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return ordersDAO.successedFiltering(iroVO);
	}

	@Override
	public int updateFilteringData(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return ordersDAO.updateFilteringData(iroVO);
	}

	@Override
	public int deleteFilteringData(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return ordersDAO.deleteFilteringData(iroVO);
	}
	
	/*고객 필터링 끝*/
	/*cs 부분 시작*/
	@Override
	public List<OrdersVO> searchCustomerOrderInfo(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ordersDAO.searchCustomerOrderInfo(osVO);
	}

	@Override
	public int searchCustomerOrderInfoCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ordersDAO.searchCustomerOrderInfoCounting(osVO);
	}

	@Override
	public List<OrdersVO> selectCustomerOrderProductInfoDetail(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return ordersDAO.selectCustomerOrderProductInfoDetail(orVO);
	}

	@Override
	@Transactional
	public boolean deleteOrders(List<String> orSerialSpecialNumberList) {
		// TODO Auto-generated method stub
		boolean orderDeleteChecking = false;
		int result = 0;
		
		for(int i=0; i<orSerialSpecialNumberList.size(); i++) {
			
			List<OrdersVO> orPkList = ordersDAO.selectOrdersPkByOrSerialSpecialNumber(orSerialSpecialNumberList.get(i));
			
			for(int j=0; j<orPkList.size(); j++) {
				boolean stockResult = stockService.updateProductChangeValues(orPkList.get(j));
				result+=ordersDAO.deleteOrdersByOrPk(orPkList.get(j));
				
				if(stockResult == false) {
					result--;
				}
			}
			
		}
		
		return true;
	}

	@Override
	@Transactional
	public int updateOrderDeliveryInvoiceNumber(List<OrdersVO> orList) {
		// TODO Auto-generated method stub
		
		int result = 0;
		for(OrdersVO orVO:  orList) {
			result += ordersDAO.updateOrderDeliveryInvoiceNumber(orVO);
			System.out.println("orVO = > "+orVO.getOrOrderNumber()+" , "+result+" 번째");
		}
		
		return result;
	}

	@Override
	@Transactional
	public boolean devideOrders(int [] orPkList) {
		// TODO Auto-generated method stub
		OrdersVO orVO =  ordersDAO.selectOrdersByPk(orPkList[0]);
		
		StoreSectionVO ssVO =  configDAO.selectStoreSectionBySspk(orVO.getSsFk());
		
		int specialNumber = ssVO.getSsSpecialNumberCount();
		
		OrdersVO tempOrdersVO = null;
		
		for(int i=0; i < orPkList.length; i++) {
			tempOrdersVO = ordersDAO.selectOrdersByPk(orPkList[i]);
			
			tempOrdersVO.setOrSerialSpecialNumber(ssVO.getSsStoreNickname()+"-"+specialNumber);
			ordersDAO.grantOrSerialSpecialNumberByOrPk(tempOrdersVO);
			ordersDAO.writeDevideOrderFlag(tempOrdersVO);

		}
		
		specialNumber++;
		
		ssVO.setSsSpecialNumberCount(specialNumber);
		
		configDAO.increaseStoreSectionSpecialNumber(ssVO);
		
		return true;
	}

	@Override
	public OrdersVO selectOrdersByPk(int orPk) {
		// TODO Auto-generated method stub
		return ordersDAO.selectOrdersByPk(orPk);
	}

	@Transactional
	@Override
	public boolean selfDevideOrders(int orPk, int orderDevideType, int radioDevideValue, int selfDevideOriginalValue,
			int selfDevideValue) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		OrdersVO ordersVO = ordersDAO.selectOrdersByPk(orPk);
		
		StoreSectionVO ssVO =  configDAO.selectStoreSectionBySspk(ordersVO.getSsFk());
		
		int specialNumber = ssVO.getSsSpecialNumberCount();
		
		OrderUtilityClass ouc = new OrderUtilityClass();
		
		int firstOrder = 0;
		int secondOrder = 0;
		
		//주문서 나누기 타입이 비율일 경우 예 : 5를 2로 나눌 경우 3, 2 로 나눠짐
		if(orderDevideType == 0) {
			firstOrder = Math.round((ordersVO.getOrAmount()/radioDevideValue)+ ordersVO.getOrAmount()%radioDevideValue) ;
			secondOrder= Math.abs(ordersVO.getOrAmount()/radioDevideValue);
			
			for(int i = 0; i < radioDevideValue; i++) {
				
				//첫번째 주문서 작업
				if(i == 0) {
					ordersDAO.updateDevideOrderData( ouc.returnDevideOrdersData(ordersVO.copy(), firstOrder, true, ordersVO.getOrSerialSpecialNumber()));
					
				//이후 주문서 작업
				}else {
					ordersDAO.insertDevideOrderData(ouc.returnDevideOrdersData(ordersVO.copy(), secondOrder, false, ssVO.getSsStoreNickname()+"-"+specialNumber));
					specialNumber++;
				
				}
			}
			
		//자문서 나누기 타입이 지정 개수일 경우 예 : 7을 6과 1로 나눔	
		}else {
			firstOrder = selfDevideOriginalValue;
			ordersDAO.updateDevideOrderData( ouc.returnDevideOrdersData(ordersVO.copy(), firstOrder, true, ordersVO.getOrSerialSpecialNumber()));
			
			secondOrder= selfDevideValue;
			ordersDAO.insertDevideOrderData(ouc.returnDevideOrdersData(ordersVO.copy(), secondOrder, false, ssVO.getSsStoreNickname()+"-"+specialNumber));
			specialNumber++;
			
		}
		
		if(specialNumber == 0) {
			
			return false;
		}
		
		stockService.updateProductChangeValues(ordersVO);
		
		ssVO.setSsSpecialNumberCount(specialNumber);
		
		configDAO.increaseStoreSectionSpecialNumber(ssVO);
		
		
		
		return true;
	}

	@Override
	public List<OrdersVO> selectCombineInfoBySerialSpecialNumber(List<String> orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return ordersDAO.selectCombineInfoBySerialSpecialNumber(orSerialSpecialNumber);
	}

	@Transactional
	@Override
	public boolean updateCombineOrders(List<String> orSerialSpecialNumber, OrdersVO combineOrderData) {
		// TODO Auto-generated method stub
		OrdersVO tempVO = null;
		
		try {			
			for(int i=0; i<orSerialSpecialNumber.size(); i++) {
				tempVO = combineOrderData.copy();
				List<OrdersVO> originalOrdersVO = ordersDAO.selectOrdersPkByOrSerialSpecialNumber(orSerialSpecialNumber.get(i));
				
				for(int j=0; j<originalOrdersVO.size(); j++) {
					tempVO.setOrPk(originalOrdersVO.get(j).getOrPk());
					ordersDAO.updateCombineOrders(tempVO);
				}
				
			}
		}catch(Exception e) {
			logger.info("합포 중 오류 발생 ");
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

	@Override
	public int changeProductAndOptionByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return ordersDAO.changeProductAndOptionByOrPk(orVO);
	}

	@Transactional
	@Override
	public int addProductAndOptionIntoOrders(List<String> orSerialSpecialNumberList, OrdersVO insertOrdersVO) {
		// TODO Auto-generated method stub
		int successedCount = 0;
		
		if(orSerialSpecialNumberList.size() == 0) {
			return successedCount;
			
		}else {
			OrdersVO originalCloneOrderVO = null;
			OrderUtilityClass ouc = new OrderUtilityClass();
			
			for(int i=0; i<orSerialSpecialNumberList.size(); i++) {
				originalCloneOrderVO = ordersDAO.selectOnlyOneOrdersAllInfoBySerialNumber(orSerialSpecialNumberList.get(i));
				ordersDAO.insertAddOrderData(ouc.returnAddProductOrdersData(insertOrdersVO, originalCloneOrderVO));
				successedCount++;
			}
			
		}
		
		return successedCount;
	}

	@Override
	public boolean deleteOrdersOne(OrdersVO orVO) {
		// TODO Auto-generated method stub
		
		int result = ordersDAO.deleteOrdersByOrPk(orVO);
		
		if(result > 0) {
			return true;
			
		}else {
			return false;
			
		}
	}

	@Override
	public List<OrdersVO> selectedOrderExcelByOrderSerachVO(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ordersDAO.selectedOrderExcelByOrderSerachVO(osVO);
	}

	@Override
	public List<OrdersVO> selectOrdersCountingByInputDate() {
		// TODO Auto-generated method stub
		return ordersDAO.selectOrdersCountingByInputDate();
	}

	@Override
	public int deleteOrdersByDate(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return ordersDAO.deleteOrdersByDate(ordersVO);
	}

	@Override
	public int outputCancledBySerialNumber(OrdersVOList orVOList) {
		// TODO Auto-generated method stub
		return ordersDAO.outputCancledBySerialNumber(orVOList);
	}

	@Override
	public int changeSendingDeadline(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ordersDAO.changeSendingDeadline(osVO);
	}

	@Override
	@Transactional
	public int updateOutputDateBySerialNumber(OrdersVOList orVOList) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		
		orVOList.setOrSerialSpecialNumber(sdf.format(now));
		
		return ordersDAO.updateOutputDateBySerialNumber(orVOList);
	}

	@Override
	public List<OrdersVOList> selectedOrderExcelByOrderSerachVOForVegit(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ordersDAO.selectedOrderExcelByOrderSerachVOForVegit(osVO);
	}

	@Override
	@Transactional
	public int devideOneOrderInManyProducts(OrdersVO orParamVO) {
		// TODO Auto-generated method stub
		String msg = "";
		
		int result = 0;
		int copyResult = 0;
		int orderNum = 1;
		
		List<ProductOptionVO> productList = ordersDAO.selectOrderInHowManyProducts(orParamVO);
		
		if(productList.size() > 1) {
			OrdersVO orVO = ordersDAO.selectOrdersByPk(orParamVO.getOrPk());
			
			OrdersVO cloneVO = null;
			
			for(int i = 0; i < productList.size(); i++) {
				try {
					cloneVO = orVO.copy();
					
					cloneVO.setOrProduct(productList.get(i).getProductName());
					cloneVO.setOrProductOption(productList.get(i).getOptionName());
					
					
					if(i != 0) {
						cloneVO.setOrProductOrderNumber("나눔"+cloneVO.getOrProductOrderNumber()+"-"+orderNum);
						cloneVO.setOrTotalPrice(0);
						cloneVO.setOrProductPrice(0);
						cloneVO.setOrProductOptionPrice(0);
						cloneVO.setOrDiscountPrice(0);
						cloneVO.setOrPaymentCommision(0);
						cloneVO.setOrAnotherPaymentCommision(0);
						cloneVO.setOrDevideFlag(true);
					}else {
						
					}
					
					result+=ordersDAO.insertDevideOrderData(cloneVO);
					
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			sedsDao.updateCancledOrder(orVO);
			stockService.updateProductChangeValues(orParamVO);
			
		}else if(productList.size() == 0){
			msg = "매칭이 되지 않은 주문서 입니다";
			
		}else {
			msg = "복수 매칭이 아닌 주문서 입니다";
		}
		
		return result;
	}

	@Override
	public OrdersVO searchRefundOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return ordersDAO.searchRefundOrder(orVO);
	}

	@Override
	public int orderRefundsEdit(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return ordersDAO.orderRefundsEdit(orVO);
	}

	@Override
	public int addCustomerSpecialRequest(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return ordersDAO.addCustomerSpecialRequest(orVO);
	}

	@Override
	public OrdersVO selectCustomerSpecialRequest(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return ordersDAO.selectCustomerSpecialRequest(orVO);
	}

	@Override
	public List<OrdersVO> selectDeliveryMsg(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return ordersDAO.selectDeliveryMsg(osVO);
	}

	@Override
	public int editDelivNum(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return ordersDAO.editDelivNum(orVO);
	}

	@Override
	public OrdersVO selectOrderQtyByPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return ordersDAO.selectOrderQtyByPk(orVO);
	}

	@Override
	@Transactional
	public String multiMatchingDevide(OrdersVO orVO) {
		// TODO Auto-generated method stub
		OrdersVO originalOrVO = ordersDAO.selectOrdersByPk(orVO.getOrPk());
		
		OrderUtilityClass ouc = new OrderUtilityClass();
		
		List<ProductOptionVO> matchingProducts = ordersDAO.selectOrdersMatchingProductByOrPk(orVO);
		
		int finalResult = matchingProducts.size();
		int result = 0;
		
		if(matchingProducts.size() <= 1) {
			return "해당 주문서는 나눌 수 없습니다";
		}
		try {
			List<OrdersVO> orList = ouc.devideMultiMatchingProduct(originalOrVO, matchingProducts);
			
			for(int i = 0; i < orList.size(); i++) {
				
				if(i == 0) {
					result+=ordersDAO.updateMultiMatchingProductOriginalOrder(orList.get(i));
				}else {
					result+=ordersDAO.insertDevideOrderData(orList.get(i));
				}
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(finalResult == result) {
			stockService.updateProductChangeValues(originalOrVO);
			return "주문서 복수 매칭 나누기 완료";
		}else {
			return "주문서 복수 매칭 나누기 실패";
		}

	}
	
	/*cs 부분 끝*/
}
