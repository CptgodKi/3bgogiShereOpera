package com.gogi.proj.stock.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;
import com.gogi.proj.paging.PaginationInfo;
import com.gogi.proj.product.options.vo.OptionsVO;
import com.gogi.proj.product.products.vo.ProductOptionVO;
import com.gogi.proj.security.AdminVO;
import com.gogi.proj.stock.model.StockService;
import com.gogi.proj.stock.vo.ProductInputListVO;
import com.gogi.proj.util.FileuploadUtil;
import com.gogi.proj.util.PageUtility;

@Controller
@RequestMapping(value="/stock")
public class StockController {

	private static final Logger logger = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	private StockService stockService;
	
	
	@Autowired
	private ConfigurationService configService;
	
	
	@Autowired
	private FileuploadUtil fileUploadUtil;
	
	@Resource(name="fileUploadProperties")
	private Properties fileProperties;
	
	/**
	 * 
	 * @MethodName : stockCheck
	 * @date : 2020. 5. 6.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 재고 체크 
	 */
	@RequestMapping(value="/stk_check.do" , method=RequestMethod.GET )
	public String stockCheck(OrderSearchVO osVO, Model model) {
		
		if(osVO.getDateStart() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));
			
		}
		
		
		stockService.stockChecking(osVO);
		
		// 재고 할당에 따른 주문서 검색 결과 가져오기
		/*int[] result = stockService.stockSearchList(osVO);*/
		
		List<Map<String, String>> searchResult = stockService.selectStockResult(osVO);
		
		//검색 조건 가져오기
		//판매처 목록
		List<StoreSectionVO> storeSectionList = configService.selectStoreSectionList();
		
		model.addAttribute("searchResult", searchResult);
		model.addAttribute("osVO", osVO);
		model.addAttribute("storeSectionList", storeSectionList);
		
		model.addAttribute("order_process", 4);
		
		return "orders/stock/stk_check";
		
	}
	
	

	/** 
	 * 
	 * @MethodName : searchOutputOrder
	 * @date : 2020. 5. 14.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 재고 체크쪽에서 
	 * 
	 * 		출고가능 목록 : searchType = outputPosiv
	 * 		미출고목록 : searchType = outputPosivNotRelease
	 * 		예약목록 : searchType = outputReserve
	 * 		재고 미할당 목록 : searchType = outputPosiv
	 * 
	 * 		버튼을 누를 경우 해당 사항에 따라 목록을 가져옴
	 */
	@RequestMapping(value="/stock_search_result.do", method=RequestMethod.GET)
	public String searchOutputOrder(OrderSearchVO osVO, Model model) {
		osVO.setDateEnd(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		if(osVO.getDateStart() == null && osVO.getDateStart().equals("")) {
			String msg = "잘못된 경로입니다";
			String url = "/stock/stk_check.do";
			
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
			return "common/message";
			
		}
		
		List<OrdersVO> stockList = stockService.searchOutputListByOutputType(osVO);
		
		model.addAttribute("stockList", stockList);
		model.addAttribute("osVO", osVO);
		
		return "orders/stock/stk_search_result";
	}
	
	
	/**
	 * 
	 * @MethodName : stockManageGet
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param model
	 * @return
	 * @메소드설명 : 재고 관리 페이지 들어가기 
	 */
	@RequestMapping(value="/manage.do", method=RequestMethod.GET)
	public String stockManageGet(Model model) {
		
		return "orders/stock/manage/add_stock";
	}
	
	
	/**
	 * 
	 * @MethodName : selectOptionStockByNameOrBarcodeNumAjax
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @return
	 * @메소드설명 : ajax로 상품명 혹은 바코드번호를 이용하여 옵션 재고 가져오기
	 */
	@RequestMapping(value="/search_option_stocks.do", method=RequestMethod.POST)
	@ResponseBody
	public List<ProductOptionVO> selectOptionStockByNameOrBarcodeNumAjax(@ModelAttribute OrderSearchVO osVO){
		
		return stockService.selectOptionStockByNameOrBarcodeNum(osVO);
	}
	
	
	/**
	 * 
	 * @MethodName : optionStockAddResult
	 * @date : 2020. 7. 17.
	 * @author : Jeon KiChan
	 * @param pilVO
	 * @return
	 * @메소드설명 : 상품 재고 증가 시키기
	 */
	@RequestMapping(value="/add_option_stocks.do", method=RequestMethod.POST, produces="application/text; charset=utf8")
	@ResponseBody
	public String optionStockAddResult(HttpServletRequest request, @ModelAttribute ProductInputListVO pilVO) {
		String msg ="";
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVO = (AdminVO)auth.getPrincipal();
		
		try {
			
			List<Map<String, Object>> fileInfo = fileUploadUtil.fileupload2(request, FileuploadUtil.STOCK_STATEMENT_IMG);
			
			logger.info("checking oriFileName = {}", fileInfo.get(0).get("oriFileName"));
			logger.info("checking uniFileName = {}", fileInfo.get(0).get("uniFileName"));
			logger.info("checking fileSize = {}", fileInfo.get(0).get("fileSize"));
			logger.info("checking fileExtType = {}", fileInfo.get(0).get("fileExtType"));
			logger.info("checking filePath = {}", fileInfo.get(0).get("filePath"));
			
			pilVO.setPilFileExe(fileInfo.get(0).get("fileExtType")+"");
			pilVO.setPilFileName(fileInfo.get(0).get("uniFileName")+"");
			pilVO.setPilFilePath(fileInfo.get(0).get("filePath")+"");
			pilVO.setPilFileOriName(fileInfo.get(0).get("oriFileName")+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("upload error! checking fileExtension or excel file");
			logger.info(e.getMessage());
			msg = "파일 업로드 실패. 로그를 확인해주세요.";
		}
		
		int result = stockService.insertProductInputList(adminVO, pilVO);
		
		if(result == 2) {
			msg = "상품 재고 증가 완료";
			
		}else if(result == 1) {
			msg = "상품 입고 승인 요청 완료";
			
		}else {
			msg = "상품 입고 요청 실패 ";
		}
		
		return msg;
	}
	
	
	/**
	 * 
	 * @MethodName : productStockAddReqList
	 * @date : 2020. 7. 20.
	 * @author : Jeon KiChan
	 * @param paging
	 * @param model
	 * @return
	 * @메소드설명 : 상품 입고 요청 목록보기
	 */
	@RequestMapping(value="/stock_req_list.do", method=RequestMethod.GET)
	public String productStockAddReqList(@ModelAttribute PaginationInfo paging, Model model) {

		if(paging.getSearchMinDate() == null) {
			
			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			paging.setSearchMinDate(sdf.format(sevenDays));
			paging.setSearchMaxDate(sdf.format(today));
			
		}
		
		int totalRecord = stockService.selectProductInputListsCount(paging);
		
		paging.setTotalRecord(totalRecord);
		
		if(paging.getBlockSize() == 0) {			
			paging.setBlockSize(10);
		}
		
		if(paging.getRecordCountPerPage() == 0) {			
			paging.setRecordCountPerPage(10);
			
		}
		
		if(totalRecord <= paging.getRecordCountPerPage()) {
			paging.setCurrentPage(1);
		}
		
		System.out.println(paging.tests());
		
		List<ProductInputListVO> pilList = stockService.selectProductInputLists(paging);
		
		model.addAttribute("pilList", pilList);
		model.addAttribute("PaginationInfo", paging);
		
		return "orders/stock/manage/stock_add_req_list";
	}
	
	
	/**
	 * 
	 * @MethodName : productStockPerm
	 * @date : 2020. 7. 20.
	 * @author : Jeon KiChan
	 * @param pilVO
	 * @param paging
	 * @param model
	 * @return
	 * @메소드설명 : 상품 입고 요청 승인하기
	 */
	@RequestMapping(value="/stock_add_res.do", method=RequestMethod.POST)
	public String productStockPerm( @ModelAttribute ProductInputListVO pilVO, @ModelAttribute PaginationInfo paging, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		AdminVO adminVO = (AdminVO)auth.getPrincipal();
		
		int result = stockService.updateProductInputList(adminVO, pilVO);
		
		if(result > 0) {
			logger.info("수량 업데이트 완료");
		}else {
			logger.info("수량 업데이트 실패");
		}
		
		model.addAttribute("PaginationInfo", paging);
		
		return "redirect:/stock/stock_req_list.do";
	}
	
	
	
	/**
	 * 
	 * @MethodName : stockStatementFileDownload
	 * @date : 2020. 7. 28.
	 * @author : Jeon KiChan
	 * @param request
	 * @param pilVO
	 * @return
	 * @메소드설명 : 입고 파일 다운로드 받기
	 */
	@RequestMapping(value="/option_stocks_file.do", method=RequestMethod.GET)
	public ModelAndView stockStatementFileDownload(HttpServletRequest request, @ModelAttribute ProductInputListVO pilVO) {
		
		String filePath = fileUploadUtil.getUploadPath(request, FileuploadUtil.STOCK_STATEMENT_IMG);
		
		 File file = new File(filePath, pilVO.getPilFileName());
		 
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);
		
		return mav;
	}
}
