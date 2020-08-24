package com.gogi.proj.configurations.model;

import java.util.List;
import java.util.Map;

import com.gogi.proj.configurations.vo.StoreSectionVO;

public interface ConfigurationDAO {

	public int addStoreSection(StoreSectionVO ssVO);
	
	public List<StoreSectionVO> selectStoreSectionList();
	
	public int increaseStoreSectionSpecialNumber(StoreSectionVO ssVO);
	
	public StoreSectionVO selectStoreSectionBySspk(int ssPk);
	
	/*public StoreExcelDataSortingVO */
	
	public int updateStoreSendingForm(StoreSectionVO ssVO);
	
	public int updateStoreSection(StoreSectionVO ssVO);
	
	public StoreSectionVO selectStoreMerge(StoreSectionVO ssVO);
	
	public int updateStoreMerge(StoreSectionVO ssVO);

}
