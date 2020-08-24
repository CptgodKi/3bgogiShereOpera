package com.gogi.proj.configurations.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.util.ConfigurationUtil;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.delivery.model.DeliveryService;
import com.gogi.proj.util.AES256Util;

@Controller
@RequestMapping(value="/config")
public class ConfigurationController {

	private static final Logger logger = LoggerFactory.getLogger(ConfigurationController.class);
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private DeliveryService deliService;
	
	/**
	 * @MethodName : storeListGet
	 * @date : 2019. 7. 17.
	 * @author : Jeon KiChan
	 * @메소드설명 : 등록된 판매처 목록 및 판매처 추가 페이지
	 */
	@RequestMapping(value="/store/list.do", method=RequestMethod.GET)
	public String storeListGet(Model model) {
		
		List<StoreSectionVO> ssList = configService.selectStoreSectionList();
		
		model.addAttribute("ssList", ssList);
		model.addAttribute("storeCounting", ssList.size());
		
		return "configuration/store/store_list";
	}
	
	/**
	 * 
	 * @MethodName : storeSendingFormGet
	 * @date : 2020. 6. 3.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 발송파일 설정 페이지
	 */
	@RequestMapping(value="/store/sending_form.do", method=RequestMethod.GET)
	public String storeSendingFormGet(@ModelAttribute StoreSectionVO ssVOPram, Model model) {
		
		StoreSectionVO ssVO = configService.selectStoreSectionBySspk(ssVOPram.getSsPk());
		
		model.addAttribute("ssVO", ssVO);
		
		return "configuration/store/config/store_sending_form";
	}
	
	/**
	 * 
	 * @MethodName : storeSendingFormPost
	 * @date : 2020. 6. 3.
	 * @author : Jeon KiChan
	 * @param ssVOPram
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 발송파일 설정 수정하기
	 */
	@RequestMapping(value="/store/sending_form.do", method=RequestMethod.POST)
	public String storeSendingFormPost(@ModelAttribute StoreSectionVO ssVOPram, Model model) {
		
		String msg = "";
		String url = "/config/store/sending_form.do?ssPk="+ssVOPram.getSsPk();
		
		int result = configService.updateStoreSendingForm(ssVOPram);
		
		if(result != 0) {
			msg = "수정 완료";
			
		}else {
			msg = "수정 실패 [로그를 확인해주세요]";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "common/message";
	}
	
	
	/**
	 * @MethodName : addStore
	 * @date : 2019. 7. 22.
	 * @author : Jeon KiChan
	 * @throws UnsupportedEncodingException 
	 * @메소드설명 : 판매처 새로 등록하기
	 */
	@RequestMapping(value="/store/add_store.do", method=RequestMethod.POST)
	public String addStore(@ModelAttribute StoreSectionVO ssVO, Model model) throws UnsupportedEncodingException {
		AES256Util aesUtil = new AES256Util();
		String msg = "";
		String url = "/config/store/list.do";
		
		String encodePass = "";
		
		int result = 0;
		
		logger.info("addStore data : StoreSectionVO, ssVO = {}", ssVO);
		
		try {
			encodePass =  aesUtil.aesEncode(ssVO.getSsStorePassword());
			logger.info("암호화 된 비밀번호 = {}",encodePass);
			
			logger.info("복호화 된 비밀번호 = {}", aesUtil.aesDecode(encodePass));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			result = configService.addStoreSection(ssVO);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			msg = "data base or parameter error";
			
		}
		
		if(result == 0) {
			msg = "값이 입력되지 않았습니다";
			
		}else if(result == 1) {
			msg = "값이 입력되지 않았습니다";
		}else if(result == 2) {
			msg = "판매처 [ "+ssVO.getSsName()+" ]등록 성공";
		}
		
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}	

	
	/**
	 * 
	 * @MethodName : storeInfoViewGet
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVOParam
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 정보 변경 페이지 들어가기
	 */
	@RequestMapping(value="/store/info.do", method=RequestMethod.GET)
	public String storeInfoViewGet(@ModelAttribute StoreSectionVO ssVOParam, Model model) {
		
		StoreSectionVO ssVO = configService.selectStoreSectionBySspk(ssVOParam.getSsPk());
		
		model.addAttribute("ssVO",ssVO);
		
		return "configuration/store/config/store_info";
	}
	
	
	/**
	 * 
	 * @MethodName : storeInfoChange
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVOParam
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 정보 변경 하기
	 */
	@RequestMapping(value="/store/info_change.do", method=RequestMethod.POST)
	public String storeInfoChange(@ModelAttribute StoreSectionVO ssVOParam, Model model) {
		
		String msg = "";
		String url = "/config/store/info.do?ssPk="+ssVOParam.getSsPk();
		
		int result = configService.updateStoreSection(ssVOParam);
		
		if(result != 0) {
			msg = "판매처 정보 변경 완료";
			
		}else {
			msg = "판매처 정보 변경 실패";
			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	
	/**
	 * 
	 * @MethodName : storeMergeConfigGet
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVOParam
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 묶음정리 조회
	 */
	@RequestMapping(value="/store/merge.do", method=RequestMethod.GET)
	public String storeMergeConfigGet(@ModelAttribute StoreSectionVO ssVOParam, Model model) {
		
		StoreSectionVO ssVO = configService.selectStoreMerge(ssVOParam);
		
		model.addAttribute("ssVO",ssVO);
		
		return "configuration/store/config/store_order_merge";
	}
	
	
	/**
	 * 
	 * @MethodName : storeMergeConfigPost
	 * @date : 2020. 6. 4.
	 * @author : Jeon KiChan
	 * @param ssVO
	 * @param model
	 * @return
	 * @메소드설명 : 판매처 묶음정리 수정
	 */
	@RequestMapping(value="/store/merge.do", method=RequestMethod.POST)
	public String storeMergeConfigPost(@ModelAttribute StoreSectionVO ssVO, Model model) {
		
		String msg = "";
		String url = "/config/store/merge.do?ssPk="+ssVO.getSsPk();
		
		int result = configService.updateStoreMerge(ssVO);
		
		if(result != 0 ) {
			msg = "판매처 묶음 정리 변경 완료";
		}else {
			msg = "판매처 묶음 정리 변경 실패";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
		
	}
}
