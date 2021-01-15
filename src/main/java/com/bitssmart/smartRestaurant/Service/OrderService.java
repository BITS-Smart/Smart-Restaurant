package com.bitssmart.smartRestaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		foodOrder = orderRepository.save(foodOrder);
		return foodOrder;
	}
}
