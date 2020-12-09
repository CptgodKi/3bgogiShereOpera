package com.gogi.proj.producttax.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.producttax.vo.ProductInfoVO;
import com.gogi.proj.producttax.vo.ResCompanyVO;

@Repository
public class ProductTaxDAOMybatis extends SqlSessionDaoSupport implements ProductTaxDAO{

	private String resCompanyNameSpace = "tax.res_company";
	private String productInfoNameSpace = "tax.product_info";
	
	@Override
	public int insertResCompany(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(resCompanyNameSpace+".insertResCompany", rcVO);
	}
	
	@Override
	public List<ResCompanyVO> selectRecCompany(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(resCompanyNameSpace+".selectRecCompany", osVO);
	}
	
	@Override
	public ResCompanyVO selectRecCompanyByRcPk(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(resCompanyNameSpace+".selectRecCompanyByRcPk", rcVO);
	}
	
	@Override
	public int selectRecCompanyCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(resCompanyNameSpace+".selectRecCompanyCounting", osVO);
	}

	@Override
	public int insertProductInfo(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(productInfoNameSpace+".insertProductInfo", piVO);
	}

	@Override
	public List<ProductInfoVO> selectProductInfoList(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(productInfoNameSpace+".selectProductInfoList", osVO);
	}

	@Override
	public int selectProductInfoListCounting(OrderSearchVO osVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(productInfoNameSpace+".selectProductInfoListCounting", osVO);
	}

	@Override
	public ProductInfoVO selectProductInfoByPiPk(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(productInfoNameSpace+".selectProductInfoByPiPk", piVO);
	}

	@Override
	public int updateTaxbilFlag(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(productInfoNameSpace+".updateTaxbilFlag", piVO);
	}

	@Override
	public int updateAccFlag(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(productInfoNameSpace+".updateAccFlag", piVO);
	}

	@Override
	public int updateProductInfo(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(productInfoNameSpace+".updateProductInfo", piVO);
	}

	@Override
	public int deleteProductInfo(ProductInfoVO piVO) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(productInfoNameSpace+".deleteProductInfo", piVO);
	}

	@Override
	public int updateResCompany(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(resCompanyNameSpace+".updateResCompany", rcVO);
	}

	@Override
	public int deleteResCompany(ResCompanyVO rcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(resCompanyNameSpace+".deleteResCompany", rcVO);
	}
	
	
}
