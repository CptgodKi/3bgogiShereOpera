package com.gogi.proj.analytics.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gogi.proj.analytics.model.AnalyticsService;
import com.gogi.proj.configurations.model.ConfigurationService;
import com.gogi.proj.configurations.vo.StoreSectionVO;
import com.gogi.proj.orders.vo.OrdersVO;
import com.gogi.proj.paging.OrderSearchVO;

@Controller
@RequestMapping(value = "/analytics")
public class AnalyticsController {

	@Autowired
	private AnalyticsService analyService;

	@Autowired
	private ConfigurationService configService;

	@RequestMapping(value = "/sevenday_total_sales_without_commsion.do", method = RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> sevendaysTotalSalesWithoutCommisionMainPage() {
		OrderSearchVO osVO = new OrderSearchVO();

		return analyService.sevendaysTotalSalesWithoutCommision(osVO);
	}

	@RequestMapping(value = "/sevenday_total_sales.do", method = RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> sevendaysTotalSalesMainPage() {
		OrderSearchVO osVO = new OrderSearchVO();

		return analyService.sevendaysTotalSales(osVO);
	}

	@RequestMapping(value = "/sevenday_sending_out.do", method = RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> sevendaysSendingOutMainPage() {
		OrderSearchVO osVO = new OrderSearchVO();

		return analyService.sevendaysSendingOut(osVO);
	}

	@RequestMapping(value = "/six_month_total_sales.do", method = RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> sixMonthTotalSalesMainPage() {
		OrderSearchVO osVO = new OrderSearchVO();

		return analyService.sixMonthTotalSales(osVO);
	}

	/**
	 * 
	 * @MethodName : selectSevenDaysOutPutProductQtyMainPage
	 * @date : 2020. 3. 6.
	 * @author : Jeon KiChan
	 * @return
	 * @메소드설명 : 7일간 접수된 물품 개수
	 */
	@RequestMapping(value = "/seven_days_pro_qty.do", method = RequestMethod.GET)
	@ResponseBody
	public List<OrdersVO> selectSevenDaysOutPutProductQtyMainPage() {

		return analyService.selectSevenDaysOutPutProductQty();
	}

	/**
	 * 
	 * @MethodName : analySearchListGet
	 * @date : 2020. 8. 5.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 기본 통계 페이지 들어가기
	 */
	@RequestMapping(value = "/analy_search_list.do", method = RequestMethod.GET)
	public String analySearchListGet(@ModelAttribute OrderSearchVO osVO, Model model) {

		if (osVO.getDateType() == null) {
			osVO.setDateType("or_regdate");
		}

		if (osVO.getDateStart() == null) {

			Calendar calendar = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			Date sevenDays = calendar.getTime();
			Date today = cal.getTime();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			osVO.setDateStart(sdf.format(sevenDays));
			osVO.setDateEnd(sdf.format(today));

		}

		// 판매처 값 가져오기
		List<StoreSectionVO> ssList = configService.selectStoreSectionList();

		model.addAttribute("ssList", ssList);
		model.addAttribute("osVO", osVO);

		return "admin/analy_search/analy_search_list";
	}

	/**
	 * 
	 * @MethodName : analySearchListPost
	 * @date : 2020. 8. 5.
	 * @author : Jeon KiChan
	 * @param osVO
	 * @param model
	 * @return
	 * @메소드설명 : 기본 통계 결과값 가져오기
	 */
	@RequestMapping(value = "/analy_search_list.do", method = RequestMethod.POST)
	public String analySearchListPost(@ModelAttribute OrderSearchVO osVO, Model model) {

		String[] wordSplit = osVO.getSsList().split(",");
		List<String> wordList = new ArrayList<String>();

		for (int j = 0; j < wordSplit.length; j++) {
			wordList.add(wordSplit[j]);
		}
		osVO.setSsPkList(wordList);

		List<Map<String, Object>> analyList = analyService.selectAnalyDataList(osVO);
		// 판매처 값 가져오기
		List<StoreSectionVO> ssList = configService.selectStoreSectionList();

		model.addAttribute("ssList", ssList);
		model.addAttribute("analyList", analyList);
		model.addAttribute("osVO", osVO);

		return "admin/analy_search/analy_search_list";
	}

	@RequestMapping(value = "/analy_search_excel_down.do", method = RequestMethod.POST)
	public ModelAndView anlaySearchResultExcelDown(@ModelAttribute OrderSearchVO osVO) {
		
		String[] wordSplit = osVO.getSsList().split(",");
		List<String> wordList = new ArrayList<String>();

		for (int j = 0; j < wordSplit.length; j++) {
			wordList.add(wordSplit[j]);
		}
		osVO.setSsPkList(wordList);

		List<Map<String, Object>> analyList = analyService.selectAnalyDataList(osVO);
		
		// 워크북 생성
		SXSSFWorkbook workbook = new SXSSFWorkbook();

		// 워크시트 생성
		SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
		// 행 생성
		SXSSFRow row = (SXSSFRow) sheet.createRow(0);
		row.setHeight((short) 500);
		// 쎌 생성
		SXSSFCell cell;

		String[] title = osVO.getGroupList().split(",");
		
		
		
		int HeaderCounting = 0;
		// 헤더 정보 구성
		for (HeaderCounting = 0; HeaderCounting < title.length; HeaderCounting++) {
			cell = (SXSSFCell) row.createCell(HeaderCounting);
			cell.setCellValue(title[HeaderCounting]);

		}
		
		
		if(osVO.getTotalList() != null) {
			String[] titleSum =  osVO.getTotalList().split(",");
			
			for (int i = 0; i < titleSum.length; i++) {
				cell = (SXSSFCell) row.createCell(HeaderCounting);
				cell.setCellValue(titleSum[i]);
				HeaderCounting++;

			}
		}

		// 리스트의 size 만큼 row를 생성

		int rowCounting = 0;
		int cellCounting = 1;
		
		for (int i = 0; i < analyList.size(); i++) {
			
			row = (SXSSFRow) sheet.createRow(cellCounting);
			
			for(Map.Entry m : analyList.get(i).entrySet()) {
				cell = (SXSSFCell) row.createCell(rowCounting);
				cell.setCellValue(m.getValue()+"");
				rowCounting++;
			}

			rowCounting = 0;
			cellCounting++;
		}

		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filedate = sdf.format(day);
		String fileWrite = "통계 결과 " + filedate + ".xlsx";

		// 입력된 내용 파일로 쓰기
		File file = new File( fileWrite);
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (fos != null)
					fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("myfile", file);
		ModelAndView mav = new ModelAndView("downloadView", fileMap);

		return mav;

	}

}
