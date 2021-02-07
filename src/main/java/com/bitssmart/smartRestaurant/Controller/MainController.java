package com.bitssmart.smartRestaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.Model.UserRoles;
import com.bitssmart.smartRestaurant.Model.Customer;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bitssmart.smartRestaurant.Service.OrderService;
import com.bitssmart.smartRestaurant.Service.RestaurantService;
import com.bitssmart.smartRestaurant.Service.UserService;
import com.bitssmart.smartRestaurant.Model.DeliveryGuy;
import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.Restaurant;
@Controller
public class MainController {

//	@RequestMapping("/home")    
//	public String home()  
//	{
//		return "restaurants";
//	}
//	@RequestMapping("/menu")    
//	public String menu()  
//	{
//		
//		return "menu";
//	}
	
//	@RequestMapping("/customerDetails")
//	@RequestMapping(value = "/customerDetails", method =RequestMethod.GET)
//	public String customerDetails(Model model){
//		Customer customer = new Customer();
//		model.addAttribute("customer",customer);
//		return "customerDetails";
//	}
//	
//	@RequestMapping(value = "/customerDetails", method =RequestMethod.POST)
//	public String mapCustomerDetails(@ModelAttribute("customer") Customer customer){
//		System.out.println(customer.getName()+" "+customer.getEmail()+" "+customer.getPhoneNumber());	
//		return "redirect:/menu";
////		return "customerDetails";
//	}
	
	
	/*
	 * @RequestMapping("/") public String home() { return "greeting"; }
	 */
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value={"/registerdelivery"},method=RequestMethod.GET)    
	public ModelAndView delRegister()  
	{
	   ModelAndView model = new ModelAndView();
	   DeliveryGuy guy = new DeliveryGuy();
	   model.addObject("guy", guy);
	   model.setViewName("deliveryRegister");
	   return model;
	}
	
	
	@RequestMapping(value={"/register"},method=RequestMethod.GET)    
	public ModelAndView register()  
	{
		ModelAndView model = new ModelAndView();
		List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		model.addObject("restaurantList",restaurants);
		User user = new User();
	   model.addObject("user", user);
	   model.setViewName("/register");
	   return model;
	}
	

	@RequestMapping(value={"/index"},method=RequestMethod.GET)    
	public ModelAndView indexuser()  
	{
	   ModelAndView model = new ModelAndView();
	   model.setViewName("index");
	   return model;
	}
	
	@RequestMapping(value={"/login"},method={RequestMethod.GET})    
	public ModelAndView login()  
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@Autowired
	 private UserService userService;
	
	@RequestMapping(value= {"/register"}, method=RequestMethod.POST)
	 public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
	  ModelAndView model = new ModelAndView();
	  User userExists = userService.findUserByEmail(user.getEmail());
	  List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		model.addObject("restaurantList",restaurants);
	  
	  if(userExists != null) {
	   bindingResult.rejectValue("email", "error.user", "This email already exists!");
	  }
	  if(bindingResult.hasErrors()) {
	   model.setViewName("register");
	  } else {
		  user.setIsEnabled(true);
		  if(user.getUserRoles().equals(UserRoles.CUSTOMER)) {
			  user.getCustomer().setUserid(user);
			  user.getCustomer().setOrderId(new ArrayList<>());
			  user.getCustomer().setIsVIP(false);
		  }
		  if(user.getUserRoles().equals(UserRoles.DELIVERY_GUY)) {
			  user.getDeliveryGuy().setUserid(user);
			  user.getDeliveryGuy().setIsApproved(false);
		  }
		  userService.saveUser(user);
		  model.addObject("msg", "User has been registered successfully!");
		  model.addObject("user", new User());
		  model.setViewName("register");
	  }
	  
	  return model;
	 }
	
	@RequestMapping(value= {"/home/index"}, method= {RequestMethod.GET,RequestMethod.POST})
	 public ModelAndView home() {
	  ModelAndView model = new ModelAndView();
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  System.out.println(auth.getName());
	  System.out.println("autho" +auth.getAuthorities());
	  User user = userService.findUserByEmail(auth.getName());
	  model.addObject("userName", user.getName());
	  System.out.println("::::"+user.getUserRoles());
	  if(user.getUserRoles().equals(UserRoles.CUSTOMER)) {
		  List<Restaurant> restaurants = restaurantService.getAllRestaurants();
		  model.addObject("restaurantList",restaurants);
		  model.setViewName("restaurants");
	  }else
	  if(user.getUserRoles().equals(UserRoles.DELIVERY_GUY)) {
		  List<FoodOrder> orderList = orderService.getAllFoodOrders();
		  System.out.println(orderList);
		  List<Long> orderIds = new ArrayList<>();
		  model.addObject("orderList",orderList); 
//		  model.addObject("orderIds",orderIds); 
		  model.setViewName("viewOrders");
	  }
	  else if(user.getUserRoles().equals(UserRoles.CHEF)) {
			   
			  model.setViewName("chefLanding");
		  }
	  
	  
	  else {
		  
		  model.setViewName("viewOrders");
	  }
	  return model;
	 }
	

}
