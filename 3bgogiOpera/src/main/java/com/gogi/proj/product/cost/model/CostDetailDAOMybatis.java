package com.gogi.proj.product.cost.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.cost.vo.CostDetailVO;
import com.gogi.proj.product.cost.vo.CostIoVO;
import com.gogi.proj.product.cost.vo.CostsVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingListVO;
import com.gogi.proj.product.options.vo.OptionsCostsMatchingVO;

@Repository
public class CostDetailDAOMybatis extends SqlSessionDaoSupport implements CostDetailDAO{

	//다중 원가 관련
	private String costs = "cost.costs";
	
	//순수 원가 관련
	private String costDetail = "cost.cost_detail";
	
	@Override
	public List<CostsVO> selectCostsPkByCostName(OptionsCostsMatchingVO ocmVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(costs+".selectCostsPkByCostName",ocmVO);
	}

	@Override
	public List<CostDetailVO> selectAllCostDetail(PaginationInfo info) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(costDetail+".selectAllCostDetail", info);
	}

	@Override
	public int insertCostDetail(CostDetailVO costDetailVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(costDetail+".insertCostDetail", costDetailVO);
	}

	@Override
	public int selectCostDetailCount(PaginationInfo info) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(costDetail+".selectCostDetailCount", info);
	}

	@Override
	public List<CostDetailVO> selectCostDetailByCcpk(int ccPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(costDetail+".selectCostDetailByCcpk", ccPk);
	}

	@Override
	public List<CostDetailVO> selectAllCostDetailJoinCostCodeList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(costDetail+".selectAllCostDetailJoinCostCodeList");
	}

	@Override
	public int insertCostsData(CostsVO costsVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(costs+".insertCostsData", costsVO);
	}

	@Override
	public int countingCostsGroupByCostName() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(costs+".countingCostsGroupByCostName");
	}

	@Override
	public CostDetailVO selectCostDetailByCcfk(CostDetailVO cdVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(costDetail+".selectCostDetailByCcfk", cdVO);
	}

	@Override
	public List<CostDetailVO> selectCostdetailWightCostcodeByCcPk(CostCodeVO ccVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(costDetail+".selectCostdetailWightCostcodeByCcPk", ccVO);
	}

}
