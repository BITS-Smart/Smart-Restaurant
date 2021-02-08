package com.bitssmart.smartRestaurant.Controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitssmart.smartRestaurant.Model.Customer;
import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.OrderType;
import com.bitssmart.smartRestaurant.Model.Payment;
import com.bitssmart.smartRestaurant.Model.PaymentOptions;
import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.ResponseVO.ShowOrderVO;
import com.bitssmart.smartRestaurant.Service.OrderService;
import com.bitssmart.smartRestaurant.Service.UserService;
import com.bitssmart.smartRestaurant.Service.CustomerService;



@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/placeOrder", method=RequestMethod.POST)    
	public ModelAndView save(@ModelAttribute("orderObj") FoodOrder foodOrder)  
	{
		//save food order & items
	//redrect to input cust details
		System.out.println(foodOrder);
		System.out.println(foodOrder.getOrderItems());
//		foodOrder.setIsPaid(false);
		foodOrder.setOrderStatus(OrderStatus.ACCEPTED);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		User user = userService.findUserByEmail(auth.getName());
		foodOrder.setCustomerID(user.getCustomer());
		System.out.println("in save order  :::"+user);
		for(int i=0; i< foodOrder.getOrderItems().size(); i++) {
			
			foodOrder.getOrderItems().get(i).setIsCancelled(false);
			foodOrder.getOrderItems().get(i).setOrderStatus(OrderStatus.ACCEPTED);
		}
		foodOrder.setOrderType(OrderType.TAKE_AWAY);
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
		
//		ModelAndView modelAndView = new ModelAndView();    
//		modelAndView.setViewName("customerDetails");
//		return new ModelAndView("redirect:/customerDetails");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderid",foodOrder.getId());
//		modelAndView.addObject("foodOrder",foodOrder);
		Customer customer = user.getCustomer();
		modelAndView.addObject("customer",customer);
		modelAndView.addObject("user",user);
		modelAndView.setViewName("customerDetails");
//		modelAndView.addObject("orderid",foodOrder.getId());
		return modelAndView;
		
		
	}
	
//	@RequestMapping("/customerDetails")
//	@RequestMapping(value = "/customerDetails", method =RequestMethod.POST)
//	public String customerDetails(Model model, @RequestParam long orderid){
//		Customer customer = new Customer();
//		FoodOrder fo = new FoodOrder();
//		fo.setId(orderid);
//		model.addAttribute("orderid",fo.getId());
//		model.addAttribute("customer",customer);
//		System.out.println("In customerDetails get method"+orderid + fo);
//		return "customerDetails";
//	}
	
	@RequestMapping(value = "/customerDetails", method =RequestMethod.POST)
	public String mapCustomerDetails(@ModelAttribute("customer") Customer customer, @ModelAttribute("orderid") FoodOrder orderid, @ModelAttribute("orderType") OrderType orderType){
		
		
		System.out.println("------------"+orderid);
		FoodOrder fo = orderService.getFoodOrder(orderid.getId());
//		System.out.println(customer.getName()+" "+customer.getEmail()+" "+customer.getPhoneNumber());
		System.out.println("::::::::::::::"+orderType);
		System.out.println("------------"+orderid);
		System.out.println(customer);
		fo.setOrderType(orderType);
		List<FoodOrder> foodList= customer.getOrderId();
		if(null != foodList) {
			foodList.add(fo);
		}
		else {
			foodList = new ArrayList<>();
			foodList.add(fo);
		}
		
		System.out.println("fhbe"+fo);
		System.out.println("Foodlist"+foodList);
		customer.setOrderId(foodList);
		customer.setIsVIP(false);
		customer.setId( null != (customerService.findByPhoneNumber(customer)) ? (customerService.findByPhoneNumber(customer)).getId() : null);
		
		customer.setCreatededAt( null != (customerService.findByPhoneNumber(customer)) ? (customerService.findByPhoneNumber(customer)).getCreatededAt() : null);
		System.out.println("GetOrderID"+customer.getOrderId());
		
		customerService.saveCustomerDetails(customer);
		
//		customer.getOrderId().get(0).setCustomerID(cs);
		
//		for(int i = 0; i< customer.getOrderId().size();i++) {
//			customer.getOrderId().get(i).setCustomerID(cs);
//		}
		

		ModelAndView modelAndView = new ModelAndView();
		//customerService.saveCustomerDetails(customer);
		System.out.println("Hvbwde"+customer.getOrderId());

		modelAndView.addObject("orderid", orderid);
		//showOrderBill(orderid.getId());
		return "redirect:/showOrderBill?orderid="+orderid.getId();
//		return "customerDetails";
	}
	

	@RequestMapping(value="/showOrderBill", method=RequestMethod.GET)    
	public ModelAndView showOrderBill( @ModelAttribute("orderid") long orderid)  
	{
		System.out.println(orderid);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("bill");
		List<ShowOrderVO> showOrderVOList=orderService.showOrderBill(orderid);
		modelAndView.addObject("orderid",orderid);
		modelAndView.addObject("showOrderBill",showOrderVOList);
		modelAndView.addObject("overAllTotalPrice", showOrderVOList.get(0).getOverAllTotalPrice());
		modelAndView.addObject("custId", showOrderVOList.get(0).getCustId());
		modelAndView.addObject("orderId", orderid);
		return modelAndView; 
	}
	
	

}
