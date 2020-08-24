package com.gogi.proj.product.cost.model;

import java.util.List;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingListVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;

public interface CostDetailDAO {

	public List<CostsVO> selectCostsPkByCostName(OptionsCostsMatchingVO ocmVO);
	
	/*원가 세부사항 가져오기*/
	public List<CostDetailVO> selectAllCostDetail(PaginationInfo info);
	
	/*원가 세부사항 삽입하기*/
	public int insertCostDetail(CostDetailVO costDetailVO);
	
	/*원가 세부사항 총 개수 파악하기*/
	public int selectCostDetailCount(PaginationInfo info);
	
	/*원가 세부사항을 원가 분류코드로 가져오기*/
	public List<CostDetailVO> selectCostDetailByCcpk(int ccPk);
	
	/*원가 세부사항과 원가 분류코드 조인하여 전부 가져오기 */
	public List<CostDetailVO> selectAllCostDetailJoinCostCodeList();
	
	/*원가 데이터 삽입*/
	public int insertCostsData(CostsVO costsVO);
	
	/*원가 개수 카운팅*/
	public int countingCostsGroupByCostName();
	
	/* 원가 사항에서 pk를 이용하여 원가사항 및 원가변동사항 전부 가져오기  */
	public CostDetailVO selectCostDetailByCcfk(CostDetailVO cdVO);
	
}
