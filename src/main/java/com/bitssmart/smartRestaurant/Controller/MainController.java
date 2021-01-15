package com.bitssmart.smartRestaurant.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitssmart.smartRestaurant.Model.Customer;

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
	
	@RequestMapping("/customerDetails")
//	@RequestMapping(value = "/customerDetails", method =RequestMethod.GET)
	public String customerDetails(Model model){
		Customer customer = new Customer();
		model.addAttribute("customer",customer);
		return "customerDetails";
	}
	
	@RequestMapping(value = "/customerDetails", method =RequestMethod.POST)
	public String mapCustomerDetails(@ModelAttribute("customer") Customer customer){
		System.out.println(customer.getName()+" "+customer.getEmail()+" "+customer.getPhoneNumber());	
		return "redirect:/menu";
//		return "customerDetails";
	}
	
	
	/*
	 * @RequestMapping("/") public String home() { return "greeting"; }
	 */
}
