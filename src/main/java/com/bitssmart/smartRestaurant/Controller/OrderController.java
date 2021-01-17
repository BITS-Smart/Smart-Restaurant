package com.bitssmart.smartRestaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.ResponseVO.ShowOrderVO;
import com.bitssmart.smartRestaurant.Service.OrderService;



@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/placeOrder", method=RequestMethod.POST)    
	public ModelAndView save(@ModelAttribute("orderObj") FoodOrder foodOrder)  
	{
		//save food order & items
	//redrect to input cust details
		System.out.println(foodOrder);
		System.out.println(foodOrder.getOrderItems());
//		foodOrder.setIsPaid(false);
		for(int i=0; i< foodOrder.getOrderItems().size(); i++) {
			
			foodOrder.getOrderItems().get(i).setIsCancelled(false);
			foodOrder.getOrderItems().get(i).setOrderStatus(OrderStatus.ORDERED);
		}
		foodOrder = orderService.saveFoodOrder(foodOrder);
		for(int i=0; i< foodOrder.getOrderItems().size(); i++) {
		foodOrder.getOrderItems().get(i).setOrderId(foodOrder);
		}
		foodOrder = orderService.saveFoodOrder(foodOrder);
		System.out.println(foodOrder);
		
		/*
		 * ModelAndView modelAndView = new ModelAndView();
		 * modelAndView.setViewName("customerDetails");
		 * modelAndView.addObject("orderid",foodOrder.getId());
		 * return modelAndView;
		 */
		return null; 
		
	}
	
	@RequestMapping(value="/showOrderBill", method=RequestMethod.POST)    
	public ModelAndView showOrderBill(@RequestParam long foodOrderId)  
	{
		System.out.println(foodOrderId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("bill");
		List<ShowOrderVO> showOrderVOList=orderService.showOrderBill(foodOrderId);
		modelAndView.addObject("showOrderBill",showOrderVOList);
		modelAndView.addObject("overAllTotalPrice", showOrderVOList.get(0).getOverAllTotalPrice());
		return modelAndView; 
	}

}
