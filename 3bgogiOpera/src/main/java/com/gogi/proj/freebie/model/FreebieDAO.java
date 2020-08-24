package com.gogi.proj.freebie.model;

import java.util.List;

import com.gogi.proj.freebie.vo.FreebieCheckVO;
import com.gogi.proj.freebie.vo.FreebieVO;
import com.gogi.proj.orders.vo.OrdersVO;

public interface FreebieDAO {

	
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
	 * @메소드설명 : 사은품 정책에 해당하는 주문서 값 가져오기
	 */
	public List<OrdersVO> selectFreebieTargetOrder(FreebieVO fbVO);
	
	
	/**
	 * 
	 * @MethodName : insertFreebieCheck
	 * @date : 2020. 6. 24.
	 * @author : Jeon KiChan
	 * @param fcVO
	 * @return
	 * @메소드설명 : 사은품이 중복으로, 삭제로 인해 또 다시 넣어지지 않도록 체크할 수 있는 테이블
	 */
	public int insertFreebieCheck(FreebieCheckVO fcVO);
	
	
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
	
	
	/**
	 * 
	 * @MethodName : selectFreebieDupliCheck
	 * @date : 2020. 8. 5.
	 * @author : Jeon KiChan
	 * @param fcVO
	 * @return
	 * @메소드설명 : 사은품 중복 여부 검사하기
	 */
	public int selectFreebieDupliCheck(FreebieCheckVO fcVO);
}
