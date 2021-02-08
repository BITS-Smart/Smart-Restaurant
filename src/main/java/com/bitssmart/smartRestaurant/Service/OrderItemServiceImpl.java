package com.bitssmart.smartRestaurant.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Bean.OrderItemBean;
import com.bitssmart.smartRestaurant.Bean.OrderItemBeanforChef;
import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderItem;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	@Override
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


	@Override
	public List<OrderItemBeanforChef> getPendingItemsForChef() {
		List<OrderStatus> oStatusList = new ArrayList<>();
		oStatusList.add(OrderStatus.ACCEPTED);
		oStatusList.add(OrderStatus.COOKED);
		oStatusList.add(OrderStatus.IS_COOKING);
		List<OrderItemBeanforChef> orderItems = orderItemRepo.getPendingOrders(oStatusList);
//		orderItemRepo.findAll().forEach(orderItems::add);
//		System.out.println(orderItems);
		return orderItems;
	}


	@Override
	public OrderItemBeanforChef getOrderItemById(long id) {
		OrderItemBeanforChef oi = orderItemRepo.getOrderItemById(id);
		return oi;
		
	}


	@Override
	public OrderItem updateOrderItem(OrderStatus orderstatus, long id) {
		OrderItem oi = orderItemRepo.updateOrderItem(orderstatus, id);
		return oi;
	}

	@Override
	public OrderItem showFoodOrderItem(long foodOrderId) {
		return orderItemRepo.findById(foodOrderId).orElse(null);
	}
	
	@Override
	public OrderItem saveFoodOrder(OrderItem orderItem) {
		orderItem = orderItemRepo.save(orderItem);
		return orderItem;
	}

	

}
