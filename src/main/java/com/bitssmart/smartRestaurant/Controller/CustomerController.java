package com.bitssmart.smartRestaurant.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.bitssmart.smartRestaurant.Model.FoodOrder;

import com.bitssmart.smartRestaurant.Service.OrderService;

@Controller
public class CustomerController {
	
	@Autowired
	private  OrderService orderService;
	
	@RequestMapping(value={"/viewPreviousOrders"},method=RequestMethod.GET)    
	public ModelAndView delRegister(HttpServletRequest request)  
	{
		@SuppressWarnings("unchecked")
		Map<String, String> messages = (Map<String, String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		Long id = Long.parseLong(messages.get("id"));
		List<FoodOrder> orders = orderService.getAllCusotmerOrders(id);
		
		ModelAndView model = new ModelAndView();
	   
		model.addObject("orders", orders);
		model.setViewName("customerPreviousOrders");
		return model;
	}

}
