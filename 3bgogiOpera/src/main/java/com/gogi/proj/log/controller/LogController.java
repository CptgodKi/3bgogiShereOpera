package com.gogi.proj.log.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gogi.proj.log.model.LogService;
import com.gogi.proj.log.vo.OrderHistoryVO;
import com.gogi.proj.orders.vo.OrdersVO;

@Controller
@RequestMapping(value="/log")
public class LogController {

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);
	
	@Autowired
	private LogService logService;
	
	
	/**
	 * 
	 * @MethodName : checkOrderHistory
	 * @date : 2020. 10. 14.
	 * @author : Jeon KiChan
	 * @param orVO
	 * @param model
	 * @return
	 * @메소드설명 : 주문서 작업 내역 확인
	 */
	@RequestMapping(value="/order_history.do", method=RequestMethod.GET)
	public String checkOrderHistory(@ModelAttribute OrdersVO orVO, Model model) {
		
		List<OrderHistoryVO> ohList = logService.selectOrderHistoryByOrPk(orVO);
		
		model.addAttribute("ohList", ohList);
		
		return "logs/order_history";
	}
	
}
