package com.gogi.proj.product.cost.model;

import java.util.List;

import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingListVO;
import com.gogi.proj.product.options.vo.OptionsVO;

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
}
