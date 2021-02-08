package com.bitssmart.smartRestaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bitssmart.smartRestaurant.Bean.OrderItemBeanforChef;
import com.bitssmart.smartRestaurant.Model.Customer;
import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderItem;
import com.bitssmart.smartRestaurant.Service.OrderItemService;
import com.bitssmart.smartRestaurant.Service.OrderService;

@Controller
public class ChefViewController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderService orderService;
	
//	 Trying to list of the orderItems.
	@GetMapping("/pendingOrders")
	public String viewOrderItems(Model model) {
		List<OrderItemBeanforChef> pendingOrders = orderItemService.getPendingItemsForChef();
		model.addAttribute("orderItems", pendingOrders);
//		for (int i = 0; i<pendingOrders.size();i++) {
//			System.out.println("This is another"+pendingOrders);
//		}
//		System.out.println("------hreer---");
//		System.out.println(pendingOrders.get(13));
		return "chefOrderView";
		
	}
	
	@GetMapping("/updateOrder/{id}")
	public String updateOrder(@PathVariable(value = "id")long id, Model model) {
		
		OrderItemBeanforChef oiForChef = orderItemService.getOrderItemById(id);
		
		model.addAttribute("orderItemById",oiForChef);
		return "updateOrderItem";
	}
	
	@PostMapping("/saveUpdates")
	public String saveUpdates(@ModelAttribute("orderItemById") OrderItemBeanforChef orderItemForChef) {
		System.out.println(orderItemForChef);
//		orderItemService.updateOrderItem(orderItemForChef.getOrderStatus(), orderItemForChef.getId());
		OrderItem oi = orderItemService.showFoodOrderItem(orderItemForChef.getId());
		oi.setOrderStatus(orderItemForChef.getOrderStatus());
		orderItemService.saveFoodOrder(oi);
//		foTemp.getOrderItems()
		return "redirect:/pendingOrders";
	}
}
