package com.gogi.proj.orders.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.configurations.vo.StoreMergeVO;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.AdminOrderRecordVO;
import com.gogi.proj.orders.vo.IrregularOrderVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.orders.vo.OrdersVOList;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;

@Repository
public class OrdersDAOMybatis extends SqlSessionDaoSupport implements OrdersDAO{

	private String orderExcelNameSpace = "order.excel";
	private String irregularNameSpace = "order.irregular";
	private String orderCsNameSpace = "order.cs";
	private String searchNameSpace = "order.search_customer_order_info";
	private String adminOrderRecordNameSpace = "order.order_record";

	@Override
	public int insertOrderData(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(orderExcelNameSpace+".insertOrderData",ordersVO);
	}

	@Override
	public List<OrdersVO> selectTotalOrderInToday() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectTotalOrderInToday");
	}

	@Override
	public int deleteOrders(int orPk) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(orderExcelNameSpace+".deleteOrders", orPk);
	}

	@Override
	public List<OrdersVO> selectOrderByOrOrderNumber(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectOrderByOrOrderNumber", ordersVO);
	}

	@Override
	public List<OrdersVO> selectNotMatchingedOrders(OrderSearchVO orderSearchVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectNotMatchingedOrders", orderSearchVO);
	}

	@Override
	public int countingNotMatchingedOrders(OrderSearchVO orderSearchVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderExcelNameSpace+".countingNotMatchingedOrders", orderSearchVO);
	}

	
	/*고객 필터링 부분*/
	@Override
	public int addIrregularOrders(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(irregularNameSpace+".addIrregularOrders", iroVO);
	}

	@Override
	public List<IrregularOrderVO> selectIrregularOrdersNotFiltered() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(irregularNameSpace+".selectIrregularOrdersNotFiltered");
	}

	@Override
	public List<IrregularOrderVO> selectIrregularOrders() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(irregularNameSpace+".selectIrregularOrders");
	}

	@Override
	public int successedFiltering(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(irregularNameSpace+".successedFiltering", iroVO);
	}

	@Override
	public int updateFilteringData(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(irregularNameSpace+".updateFilteringData", iroVO);
	}

	@Override
	public int deleteFilteringData(IrregularOrderVO iroVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(irregularNameSpace+".deleteFilteringData", iroVO);
	}
	/*고객 필터링 끝*/

	/*묶음정리 기능 관련 시작*/
	@Override
	public List<OrdersVO> selectNotMergedOrders(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectNotMergedOrders", ssVO);
	}

	@Override
	public int grantOrSerialSpecialNumber(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".grantOrSerialSpecialNumber", ordersVO);
	}

	@Override
	public List<OrdersVO> selectOrdersByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectOrdersByOrderNumber", orderNumber);
	}

	@Override
	public List<OrdersVO> searchCustomerOrderInfo(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".searchCustomerOrderInfo", osVO);
	}

	@Override
	public int searchCustomerOrderInfoCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".searchCustomerOrderInfoCounting", osVO);
	}

	@Override
	public List<OrdersVO> selectCustomerOrderProductInfoDetail(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectCustomerOrderProductInfoDetail", orVO);
	}

	@Override
	public List<OrdersVO> selectOrdersPkByOrSerialSpecialNumber(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectOrdersPkByOrSerialSpecialNumber", orSerialSpecialNumber);
	}

	@Override
	public int deleteOrdersByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(orderCsNameSpace+".deleteOrdersByOrPk", orVO);
	}
	
	/*묶음정리 기능 관련 끝*/
	
	@Override
	public int updateOrderDeliveryInvoiceNumber(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		
		return getSqlSession().update(orderExcelNameSpace+".updateOrderDeliveryInvoiceNumber", ordersVO);
	}

	@Override
	public OrdersVO selectOrdersByPk(int orPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectOrdersByPk", orPk);
	}

	@Override
	public int grantOrSerialSpecialNumberByOrPk(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".grantOrSerialSpecialNumberByOrPk", ordersVO);
	}

	@Override
	public List<OrdersVO> selectNotMatchingedCostData() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectNotMatchingedCostData");
	}

	@Override
	public int updateOrderCostsData(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".updateOrderCostsData", ordersVO);
	}

	@Override
	public int insertDevideOrderData(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(orderExcelNameSpace+".insertDevideOrderData", ordersVO);
	}

	@Override
	public int updateDevideOrderData(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".updateDevideOrderData", ordersVO);
	}

	@Override
	public List<OrdersVO> selectCombineInfoBySerialSpecialNumber(List<String> orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectCombineInfoBySerialSpecialNumber", orSerialSpecialNumber);
	}

	@Override
	public int updateCombineOrders(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".updateCombineOrders", orVO);
	}

	@Override
	public int changeProductAndOptionByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".changeProductAndOptionByOrPk", orVO);
	}

	@Override
	public OrdersVO selectOnlyOneOrdersAllInfoBySerialNumber(String orSerialSpecialNumber) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectOnlyOneOrdersAllInfoBySerialNumber", orSerialSpecialNumber);
	}

	@Override
	public int insertAddOrderData(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(orderExcelNameSpace+".insertAddOrderData", orVO);
	}

	@Override
	public List<OrdersVO> selectedOrderExcelByOrderSerachVO(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectedOrderExcelByOrderSerachVO", osVO);
	}

	@Override
	public List<OrdersVO> selectOrdersCountingByInputDate() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectOrdersCountingByInputDate");
	}

	@Override
	public int deleteOrdersByDate(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(orderExcelNameSpace+".deleteOrdersByDate", ordersVO);
	}

	@Override
	public int outputCancledBySerialNumber(OrdersVOList orVOList) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".outputCancledBySerialNumber", orVOList);
	}

	@Override
	public int changeSendingDeadline(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".changeSendingDeadline", osVO);
	}

	@Override
	public int writeDevideOrderFlag(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderExcelNameSpace+".writeDevideOrderFlag", orVO);
	}

	@Override
	public int updateOutputDateBySerialNumber(OrdersVOList orVOList) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".updateOutputDateBySerialNumber", orVOList);
	}

	@Override
	public List<OrdersVOList> selectedOrderExcelByOrderSerachVOForVegit(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderExcelNameSpace+".selectedOrderExcelByOrderSerachVOForVegit", osVO);
	}

	@Override
	public List<ProductOptionVO> selectOrderInHowManyProducts(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectOrderInHowManyProducts", orVO);
	}

	@Override
	public OrdersVO searchRefundOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".searchRefundOrder", orVO);
	}

	@Override
	public int orderRefundsEdit(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".orderRefundsEdit", orVO);
	}

	@Override
	public int addCustomerSpecialRequest(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".addCustomerSpecialRequest", orVO);
	}

	@Override
	public OrdersVO selectCustomerSpecialRequest(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectCustomerSpecialRequest", orVO);
	}

	@Override
	public List<OrdersVO> selectDeliveryMsg(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectDeliveryMsg", osVO);
	}

	@Override
	public int editDelivNum(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".editDelivNum", orVO);
	}

	@Override
	public OrdersVO selectOrderQtyByPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectOrderQtyByPk", orVO);
	}

	@Override
	public int updateMultiMatchingProductOriginalOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".updateMultiMatchingProductOriginalOrder", orVO);
	}

	@Override
	public List<ProductOptionVO> selectOrdersMatchingProductByOrPk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectOrdersMatchingProductByOrPk", orVO);
	}

	@Override
	public int updateExcelDivOrders(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".updateExcelDivOrders", orVO);
	}

	@Override
	public List<OrdersVO> newSearchCustomerOrderInfo(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(searchNameSpace+".newSearchCustomerOrderInfo", osVO);
	}

	@Override
	public int newSearchCustomerOrderInfoCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(searchNameSpace+".newSearchCustomerOrderInfoCounting", osVO);
	}

	@Override
	public List<OrdersVO> newSearchCustomerOrderInfoToExcelFile(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(searchNameSpace+".newSearchCustomerOrderInfoToExcelFile", osVO);
	}

	@Override
	public List<OrdersVO> selectCreateInvoiceNum() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(orderCsNameSpace+".selectCreateInvoiceNum");
	}

	@Override
	public OrdersVO selectBuyerAddrInfo(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(orderCsNameSpace+".selectBuyerAddrInfo", orVO);
	}

	@Override
	public int checkDepositOrder(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".checkDepositOrder", orVO);
	}

	@Override
	public int receiverPickUp(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(orderCsNameSpace+".receiverPickUp", orVO);
	}

	@Override
	public int deleteExcelGiftOrderByOrFk(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(orderExcelNameSpace+".deleteExcelGiftOrderByOrFk", orVO);
	}

	@Override
	public int insertAdminOrderRecord(AdminOrderRecordVO aorVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(adminOrderRecordNameSpace+".insertAdminOrderRecord", aorVO);
	}

	@Override
	public List<AdminOrderRecordVO> searchAdminOrderRecordBySerialSpecialNumber(OrdersVO orVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(adminOrderRecordNameSpace+".searchAdminOrderRecordBySerialSpecialNumber", orVO);
	}
}
