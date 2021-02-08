package com.bitssmart.smartRestaurant.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitssmart.smartRestaurant.Bean.OrderItemBean;
import com.bitssmart.smartRestaurant.Service.OrderItemService;

@Controller
public class ReportController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@RequestMapping("/reports")
	public String seeReports(Model model) {
		
		List<OrderItemBean> ob  = orderItemService.getTopFiveOrderItems();
		System.out.println(ob.get(0).getPrice());
		model.addAttribute("orderItems",ob);
//		model.addAllAttributes(ob);
//		System.out.println(orderItemService.getTopFiveOrderItems());
		return "managerReports";
		
	}

}
