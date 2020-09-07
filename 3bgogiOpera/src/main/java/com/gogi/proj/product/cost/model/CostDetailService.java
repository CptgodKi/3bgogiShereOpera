package com.gogi.proj.product.cost.model;

import java.util.List;

import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingListVO;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.vo.CarcassInputListVO;

public interface CostDetailService {
	
	public int selectCostsPkByCostNameAndInsertMatchingData(OptionsVO optionVO);
	
	public List<CostDetailVO> selectAllCostDetail(PaginationInfo info);
	
	public int insertCostDetail(CostDetailVO costDetailVO);

	public int selectCostDetailCount(PaginationInfo info);
	
	public List<CostDetailVO> selectCostDetailByCcpk(int ccPk);
	
	public List<CostDetailVO> selectAllCostDetailJoinCostCodeList();
	
	public int insertCostsData(CostsVO costsVO);
	
	public int countingCostsGroupByCostName();
	
	public CostDetailVO selectCostDetailByCcfk(CostDetailVO cdVO);
	
	/**
	 * 
	 * @MethodName : selectCostdetailWightCostcodeByCcPk
	 * @date : 2020. 8. 24.
	 * @author : Jeon KiChan
	 * @param ccVO
	 * @return
	 * @메소드설명 : cost_code의 pk 값으로 cost_detail 목록 가져오기
	 */
	public List<CostDetailVO> selectCostdetailWightCostcodeByCcPk(CostCodeVO ccVO);
	
	
	/**
	 * 
	 * @MethodName : insertCarcassAndCostIo
	 * @date : 2020. 8. 26.
	 * @author : Jeon KiChan
	 * @param cilVO
	 * @return
	 * @메소드설명 : 도체 및 부분육 입력하기
	 */
	public int insertCarcassAndCostIo(AdminVO adminVO, CarcassInputListVO cilVO);
	
	
	/**
	 * 
	 * @MethodName : selectCarcassInputList
	 * @date : 2020. 8. 27.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : 도체 입력 내역 가져오기
	 */
	public List<CarcassInputListVO> selectCarcassInputList(OrderSearchVO osVO);
}
