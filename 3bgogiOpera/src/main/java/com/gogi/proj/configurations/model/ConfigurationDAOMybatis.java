package com.gogi.proj.configurations.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.gogi.proj.configurations.vo.StoreSectionVO;

@Repository
public class ConfigurationDAOMybatis extends SqlSessionDaoSupport implements ConfigurationDAO{

	private String namespace = "configuration.store";

	@Override
	public int addStoreSection(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().insert(namespace+".addStoreSection", ssVO);
	}

	@Override
	public List<StoreSectionVO> selectStoreSectionList() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace+".selectStoreSectionList");
	}

	@Override
	public int increaseStoreSectionSpecialNumber(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".increaseStoreSectionSpecialNumber", ssVO);
	}

	@Override
	public StoreSectionVO selectStoreSectionBySspk(int ssPk) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectStoreSectionBySspk", ssPk);
	}

	@Override
	public int updateStoreSendingForm(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateStoreSendingForm", ssVO);
	}

	@Override
	public int updateStoreSection(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateStoreSection", ssVO);
	}

	@Override
	public StoreSectionVO selectStoreMerge(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(namespace+".selectStoreMerge", ssVO);
	}

	@Override
	public int updateStoreMerge(StoreSectionVO ssVO) {
		// TODO Auto-generated method stub
		return getSqlSession().update(namespace+".updateStoreMerge", ssVO);
	}
	
}
