package com.bitssmart.smartRestaurant.Controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitssmart.smartRestaurant.Model.DeliveryGuy;
import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.Service.DeliveryGuyService;
import com.bitssmart.smartRestaurant.Service.UserService;

@Controller
public class DeliveryGuyController {

	@Autowired
	private DeliveryGuyService deliveryGuyService;
	
	
	@RequestMapping(value= {"/registerGuy"}, method=RequestMethod.POST)
	 public ModelAndView createUser(@Valid DeliveryGuy guy, BindingResult bindingResult) {
	  ModelAndView model = new ModelAndView();
	  DeliveryGuy guyExists = deliveryGuyService.findUserByEmail(guy.getEmail());
	  
	  if(guyExists != null) {
	   bindingResult.rejectValue("email", "error.user", "This email already exists!");
	  }
	  if(bindingResult.hasErrors()) {
	   model.setViewName("deliveryRegister");
	  } else {
		  guy.setIsEnabled(true);
		  guy.setIsApproved(false);
		  deliveryGuyService.saveUser(guy);
		  model.addObject("msg", "Delivery Guy has been registered successfully!");
		  model.addObject("guy", new DeliveryGuy());
		  model.setViewName("deliveryRegister");
	  }
	  
	  return model;
	 }
	
	@RequestMapping(value={"/loginDeliveryGuy"},method={RequestMethod.GET})    
	public ModelAndView login()  
	{
		ModelAndView model = new ModelAndView();
		model.setViewName("deliveryGuyLogin");
		return model;
	}
	
	//@AuthenticationPrincipal Principal user
	@RequestMapping(value= {"/deliveryGuy/orders"}, method= {RequestMethod.POST})
	 public ModelAndView home() {
	  ModelAndView model = new ModelAndView();
	  Authentication user = SecurityContextHolder.getContext().getAuthentication();
	  //System.out.println(auth);
	  //System.out.println(auth.getName());
	  System.out.println("cred" +user.getCredentials());
	  System.out.println("details" +user.getDetails());
	  System.out.println("princ" +user.getPrincipal());
	  System.out.println("autho" +user.getAuthorities());
	  DeliveryGuy guyExists = deliveryGuyService.findUserByEmail(user.getName());
	  System.out.println("guy  " +guyExists);
		 if(null != guyExists) model.addObject("userName", guyExists.getName()); 
		 else
		  model.addObject("userName", "abc");
	  model.setViewName("viewOrders");
	  return model;
	 }
}
