package com.gogi.proj.freebie.model;

import java.util.List;

import com.gogi.proj.freebie.vo.FreebieVO;
import com.gogi.proj.orders.vo.OrdersVO;

public interface FreebieService {

	/**
	 * 
	 * @MethodName : insertFreebie
	 * @date : 2020. 6. 22.
	 * @author : Jeon KiChan
	 * @param fbVO
	 * @return
	 * @메소드설명 : 사은품 정책 생성
	 */
	public int insertFreebie(FreebieVO fbVO);
	
	/**
	 * 
	 * @MethodName : selectFreebieList
	 * @date : 2020. 6. 23.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 모든 사은품 정책 가져오기
	 */
	public List<FreebieVO> selectFreebieList();
	
	/**
	 * 
	 * @MethodName : selectFreebieByFbpk
	 * @date : 2020. 6. 23.
	 * @author : Jeon KiChan
	 * @param fbVO
	 * @return
	 * @메소드설명 :  fbPk 값으로 사은품 정책 가져오기
	 */
	public FreebieVO selectFreebieByFbpk(FreebieVO fbVO);
	
	/**
	 * 
	 * @MethodName : selectFreebieTargetOrder
	 * @date : 2020. 6. 23.
	 * @author : Jeon KiChan
	 * @param fbVO
	 * @return
	 * @메소드설명 : 사은품 정책 적용
	 */
	public int selectFreebieTargetOrder(FreebieVO fbVO);
	
	/**
	 * 
	 * @MethodName : updateFreebieByPk
	 * @date : 2020. 6. 25.
	 * @author : Jeon KiChan
	 * @param fbVO
	 * @return
	 * @메소드설명 : 사은품 수정
	 */
	public int updateFreebieByPk(FreebieVO fbVO);
}
