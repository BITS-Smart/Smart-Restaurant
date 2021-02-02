package com.bitssmart.smartRestaurant.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Bean.OrderItemBean;
import com.bitssmart.smartRestaurant.Model.OrderItem;
import com.bitssmart.smartRestaurant.Repository.OrderItemRepository;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	
	public List<OrderItemBean> getTopFiveOrderItems(){
		
		List<OrderItemBean> orderItems = orderItemRepo.findFirst5ByMenuItemId();
//		System.out.println(value);
//		orderItemRepo.findAll().forEach(orderItems::add);
//		
//		for (Object items:orderItems) {
//			System.out.println("---"+items);
//		}
//		System.out.println("---"+orderItems);
//		orde
//		System.out.println(orderItemRepo.getTopFiveOrderItems());
		return orderItems;
		
	}

}
