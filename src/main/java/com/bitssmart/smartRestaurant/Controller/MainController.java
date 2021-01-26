package com.bitssmart.smartRestaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.Model.Customer;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.bitssmart.smartRestaurant.Service.UserService;
import com.bitssmart.smartRestaurant.Model.DeliveryGuy;
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
	  
	  if(userExists != null) {
	   bindingResult.rejectValue("email", "error.user", "This email already exists!");
	  }
	  if(bindingResult.hasErrors()) {
	   model.setViewName("register");
	  } else {
		  user.setIsEnabled(true);
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
	  User user = userService.findUserByEmail(auth.getName());
	  model.addObject("userName", user.getName());
	  model.setViewName("viewOrders");
	  return model;
	 }
	

}
