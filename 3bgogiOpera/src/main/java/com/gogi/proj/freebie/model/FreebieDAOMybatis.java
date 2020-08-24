package com.gogi.proj.freebie.model;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.freebie.vo.FreebieCheckVO;
import com.gogi.proj.freebie.vo.FreebieVO;
import com.gogi.proj.orders.vo.OrdersVO;

@Repository
public class FreebieDAOMybatis extends SqlSessionDaoSupport implements FreebieDAO{

	private String namespace = "freebie";

	@Override
	public int insertFreebie(FreebieVO fbVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertFreebie", fbVO);
	}

	@Override
	public List<FreebieVO> selectFreebieList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectFreebieList");
	}

	@Override
	public FreebieVO selectFreebieByFbpk(FreebieVO fbVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectFreebieByFbpk", fbVO);
	}

	@Override
	public List<OrdersVO> selectFreebieTargetOrder(FreebieVO fbVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectFreebieTargetOrder", fbVO);
	}

	@Override
	public int insertFreebieCheck(FreebieCheckVO fcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".insertFreebieCheck", fcVO);
	}

	@Override
	public int updateFreebieByPk(FreebieVO fbVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateFreebieByPk", fbVO);
	}

	@Override
	public int selectFreebieDupliCheck(FreebieCheckVO fcVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectFreebieDupliCheck", fcVO);
	}
	
}
