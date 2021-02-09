package com.bitssmart.smartRestaurant.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitssmart.smartRestaurant.Bean.OrderItemBean;
import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.Restaurant;
import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.Service.OrderItemService;
import com.bitssmart.smartRestaurant.Service.OrderService;
import com.bitssmart.smartRestaurant.Service.RestaurantService;
import com.bitssmart.smartRestaurant.Service.UserService;

@Controller
public class ManagerViewController {
	
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	 private UserService userService;
	@Autowired
	private  OrderService orderService;
	
	@RequestMapping("/reports")
	public String seeReports(Model model) {
		
		List<OrderItemBean> ob  = orderItemService.getTopFiveOrderItems();
		System.out.println(ob.get(0).getPrice());
		model.addAttribute("orderItems",ob);
//		model.addAllAttributes(ob);
//		System.out.println(orderItemService.getTopFiveOrderItems());
		return "managerReports";
		
	}
	
	@RequestMapping(value={"/viewAllOrders"},method=RequestMethod.GET)    
	public ModelAndView delRegister(HttpServletRequest request)  
	{
		@SuppressWarnings("unchecked")
		Map<String, String> messages = (Map<String, String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		String email = String.valueOf(messages.get("email"));
		User user = userService.findUserByEmail(email);
		List<FoodOrder> orders = orderService.getAllRestaurantOrders(user.getRestaurantId().getId());
		
		ModelAndView model = new ModelAndView();
	   
		model.addObject("resName", user.getRestaurantId().getName());
		model.addObject("orders", orders);
		model.setViewName("restaurantOrders");
		return model;
	}
	
	@RequestMapping("/managerHome")
	public String managerHome(HttpServletRequest request,Model model) {
		@SuppressWarnings("unchecked")
		Map<String, String> messages = (Map<String, String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
		String email = String.valueOf(messages.get("email"));
		User user = userService.findUserByEmail(email);
		System.out.println("::::"+user);
		  model.addAttribute("userName", user.getName());
		model.addAttribute("restaurantIndex",user.getRestaurantId().getId()); 
		return "managerHome";
		
	}

}
