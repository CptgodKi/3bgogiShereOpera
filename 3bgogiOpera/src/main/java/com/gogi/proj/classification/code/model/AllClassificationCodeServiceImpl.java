package com.gogi.proj.classification.code.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.proj.classification.code.vo.ClassificationVO;
import com.gogi.proj.classification.code.vo.CostCodeVO;
import com.gogi.proj.classification.code.vo.ExcelOrderSeqVO;
import com.gogi.proj.classification.code.vo.PackingCombineCodeVO;

@Service
public class AllClassificationCodeServiceImpl implements AllClassificationCodeService{

	@Autowired
	private AllClassificationCodeDAO accDao;
	
	//원가 카테고리 리스트 가져오기
	@Override
	public List<CostCodeVO> selectCostCodeList() {
		// TODO Auto-generated method stub
		return accDao.selectCostCodeList();
	}
	
	//상품 카테고리 리스트 가져오기
	@Override
	public List<ClassificationVO> selectClassificationList() {
		// TODO Auto-generated method stub
		return accDao.selectClassificationList();
	}

	@Override
	public List<ExcelOrderSeqVO> selectExcelOrderSeqCodeList() {
		// TODO Auto-generated method stub
		return accDao.selectExcelOrderSeqCodeList();
	}

	@Override
	public List<PackingCombineCodeVO> selectPackingCombineCodeList() {
		// TODO Auto-generated method stub
		return accDao.selectPackingCombineCodeList();
	}

	@Override
	public List<ClassificationVO> selectCfListOrderbyCfPk() {
		// TODO Auto-generated method stub
		return accDao.selectCfListOrderbyCfPk();
	}
	
	
}
