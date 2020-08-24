package com.gogi.proj.configurations.model;

import java.util.List;

import com.gogi.proj.configurations.vo.StoreSectionVO;

public interface ConfigurationService {

	public int addStoreSection(StoreSectionVO ssVO);
	
	public List<StoreSectionVO> selectStoreSectionList();
	
	public StoreSectionVO selectStoreSectionBySspk(int ssPk);
	
	public int updateStoreSendingForm(StoreSectionVO ssVO);
	
	public int updateStoreSection(StoreSectionVO ssVO);
	
	public StoreSectionVO selectStoreMerge(StoreSectionVO ssVO);
	
	public int updateStoreMerge(StoreSectionVO ssVO);
}
