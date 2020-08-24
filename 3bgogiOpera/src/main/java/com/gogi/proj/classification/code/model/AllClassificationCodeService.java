package com.gogi.proj.classification.code.model;

import java.util.List;

import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;
import com.gogi.proj.classification.code.vo.PackingCombineCodeVO;

public interface AllClassificationCodeService {

	public List<CostCodeVO> selectCostCodeList();
	
	public List<ClassificationVO> selectClassificationList();
	
	public List<ExcelOrderSeqVO> selectExcelOrderSeqCodeList();
	
	public List<PackingCombineCodeVO> selectPackingCombineCodeList();
	
	public List<ClassificationVO> selectCfListOrderbyCfPk();
}
