package com.bitssmart.smartRestaurant.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitssmart.smartRestaurant.Model.DeliveryGuy;
import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.Service.DeliveryGuyService;
import com.bitssmart.smartRestaurant.Service.OrderService;
import com.bitssmart.smartRestaurant.Service.UserService;

@Controller
public class DeliveryGuyController {

	@Autowired
	private DeliveryGuyService deliveryGuyService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
//	@RequestMapping(value= {"/registerGuy"}, method=RequestMethod.POST)
//	 public ModelAndView createUser(@Valid DeliveryGuy guy, BindingResult bindingResult) {
//	  ModelAndView model = new ModelAndView();
//	  DeliveryGuy guyExists = deliveryGuyService.findUserByEmail(guy.getEmail());
//	  
//	  if(guyExists != null) {
//	   bindingResult.rejectValue("email", "error.user", "This email already exists!");
//	  }
//	  if(bindingResult.hasErrors()) {
//	   model.setViewName("deliveryRegister");
//	  } else {
//		  guy.setIsEnabled(true);
//		  guy.setIsApproved(false);
//		  deliveryGuyService.saveUser(guy);
//		  model.addObject("msg", "Delivery Guy has been registered successfully!");
//		  model.addObject("guy", new DeliveryGuy());
//		  model.setViewName("deliveryRegister");
//	  }
//	  
//	  return model;
//	 }
	private static List<FoodOrder> foodOrderList = new ArrayList<>();;
	
	@RequestMapping(value={"/loginDeliveryGuy"},method={RequestMethod.GET})    
	public ModelAndView login()  
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("deliveryGuyLogin");
		return model;
	}
	
	//@AuthenticationPrincipal Principal user
//	@RequestMapping(value= {"/deliveryGuy/orders"}, method= {RequestMethod.POST})
//	 public ModelAndView home() {
//	  ModelAndView model = new ModelAndView();
//	  Authentication user = SecurityContextHolder.getContext().getAuthentication();
//	  System.out.println(user.getName());
//	  //System.out.println(auth.getName());
//	  System.out.println("cred" +user.getCredentials());
//	  System.out.println("details" +user.getDetails());
//	  System.out.println("princ" +user.getPrincipal());
//	  System.out.println("autho" +user.getAuthorities());
//	  DeliveryGuy guyExists = deliveryGuyService.findUserByEmail(user.getName());
//	  System.out.println("guy  " +guyExists);
//		 if(null != guyExists) model.addObject("userName", guyExists.getName()); 
//		 else
//		  model.addObject("userName", "abc");
//		 
//	  List<FoodOrder> orderList = orderService.getAllFoodOrders();
//	  model.addObject("orderList",orderList); 
//	  model.setViewName("viewOrders");
//	  return model;
//	 }
	
//@ModelAttribute("orderIds") List<Long> orderIds
	@RequestMapping(value={"/refreshOrders"},method={RequestMethod.POST})    
	public ModelAndView selectOrders( )  
	{
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getName());
		List<FoodOrder> orderList = orderService.getAllFoodOrders();
		System.out.println(orderList);
		model.addObject("orderList",orderList); 
		model.setViewName("viewOrders");
		return model;
	}
	
	@RequestMapping(value={"/selectOrders"},method={RequestMethod.POST})    
	public ModelAndView selectOrders( @RequestParam(value = "orderIds" , required = false) long[] orderIds )  
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		User user = userService.findUserByEmail(auth.getName());
		//List<FoodOrder> foodOrderList = new ArrayList<>();
		foodOrderList.clear();
		if(null != orderIds) {
			for(long a : orderIds) {
				FoodOrder fo = orderService.getFoodOrder(a);
				fo.setUserId(user);
				fo.setOrderStatus(OrderStatus.PICKED_UP);
				fo = orderService.saveFoodOrder(fo);
				foodOrderList.add(fo);
			}
		}
		ModelAndView model = new ModelAndView();
		model.addObject("userName", user.getName());
		model.addObject("foodOrderList", foodOrderList);
//		model.addObject("orderIds", orderIds);
		model.addObject("points", user.getDeliveryGuy().getPoints());
		model.setViewName("updateOrderDetails");
		return model;
	}
	
	@RequestMapping(value={"/orderDelivered"},method={RequestMethod.POST})    
	public ModelAndView selectOrders( @RequestParam("orderid") long orderid , @RequestParam("otp") String otp )  
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		User user = userService.findUserByEmail(auth.getName());
		FoodOrder fo1 = orderService.getFoodOrder(orderid);
		System.out.println(fo1);
		System.out.println("::::::::::::::::::::::::::: "+foodOrderList);
		ModelAndView model = new ModelAndView();
		if(null!= otp && fo1.getOtp().equalsIgnoreCase(otp.trim())) {
			for(int i=0; i< foodOrderList.size(); i++) {
				if(foodOrderList.get(i).getId().equals(orderid)) {
					foodOrderList.remove(i);
				}
			}
			fo1.setOrderStatus(OrderStatus.DELIVERED);
			fo1.getUserId().getDeliveryGuy().setPoints(fo1.getUserId().getDeliveryGuy().getPoints()+5);
			fo1 = orderService.saveFoodOrder(fo1);
			System.out.println(":::::delivery status"+ fo1.getOrderStatus());
			model.addObject("msg", "Delivery Successful!");
			//foodOrderList.add(fo1);
		}else {
			model.addObject("msg", "Wrong OTP!");
		}
		//List<FoodOrder> foodOrderList = new ArrayList<>();
//		if(null != orderIds) {
//			for(long a : orderIds) {
//				FoodOrder fo = orderService.getFoodOrder(a);
//				foodOrderList.add(fo);
//			}
//		}
		model.addObject("userName", user.getName());
		model.addObject("foodOrderList", foodOrderList);
//		model.addObject("orderIds", orderIds);
		model.addObject("points", user.getDeliveryGuy().getPoints());
		model.setViewName("updateOrderDetails");
		return model;
	}
}
