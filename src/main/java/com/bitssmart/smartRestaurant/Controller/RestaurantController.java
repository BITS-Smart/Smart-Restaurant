package com.bitssmart.smartRestaurant.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitssmart.smartRestaurant.Model.FoodCategory;
import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderItem;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.Restaurant;
import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.Service.RestaurantService;
import com.bitssmart.smartRestaurant.Service.UserService;
@Controller
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	 private UserService userService;
	
	@RequestMapping("/home")    
	public ModelAndView home()  
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		User user = userService.findUserByEmail(auth.getName());
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		ModelAndView modelAndView = new ModelAndView();   
		modelAndView.addObject("restaurantList",restaurants);
		modelAndView.setViewName("restaurants");
		return modelAndView;
	}
	
	@RequestMapping(value ="/restaurant", method=RequestMethod.POST)    
	public ModelAndView restaurantHome(@ModelAttribute("restaurantIndex") Long id )  
	{
		System.out.println("I am here"+id);
		Restaurant restaurant = restaurantService.getRestaurant(id);
		ModelAndView modelAndView = new ModelAndView();   
		modelAndView.addObject("restaurantId",id);
		modelAndView.addObject("restaurant",restaurant);
		modelAndView.setViewName("index");     
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/menu", method=RequestMethod.POST)    
	public ModelAndView menu(@ModelAttribute("restaurantIndex") Long id)  
	{
		Restaurant restaurant = restaurantService.getRestaurant(id);
		System.out.println(restaurant);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		User user = userService.findUserByEmail(auth.getName());
		System.out.println("in menu  :::"+user);
		ModelAndView modelAndView = new ModelAndView();    
		modelAndView.setViewName("menu");        
		
		modelAndView.addObject("restaurant",restaurant);
		modelAndView.addObject("foodCategory", Arrays.asList(FoodCategory.values()));
		System.out.println( Arrays.asList(FoodCategory.values()));
		modelAndView.addObject("orderObj", new FoodOrder());
		modelAndView.addObject("orderStatus", OrderStatus.ACCEPTED);
		modelAndView.addObject("orderStatusBool", false);
		return modelAndView;
	}
	
	
}
