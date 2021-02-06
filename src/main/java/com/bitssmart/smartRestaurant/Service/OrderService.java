package com.bitssmart.smartRestaurant.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderItem;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.OrderType;
import com.bitssmart.smartRestaurant.Model.Restaurant;
import com.bitssmart.smartRestaurant.Repository.OrderRepository;
import com.bitssmart.smartRestaurant.ResponseVO.ShowOrderVO;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public FoodOrder saveFoodOrder(FoodOrder foodOrder) {
		foodOrder = orderRepository.save(foodOrder);
		return foodOrder;
	}
	
	public FoodOrder showFoodOrder(long foodOrderId) {
		return orderRepository.findById(foodOrderId).orElse(null);
	}
	
	public List<ShowOrderVO> showOrderBill(long foodOrderId) {
		List<ShowOrderVO> showOrderVOList= new ArrayList<>();
		int id=1;
		FoodOrder foodOrder = orderRepository.findById(foodOrderId).orElse(null);
			for(OrderItem item:foodOrder.getOrderItems()) {
			ShowOrderVO showOrderVO=new ShowOrderVO();
			showOrderVO.setId(id);
			showOrderVO.setMenuItemName(item.getMenuItemId().getName());
			showOrderVO.setPrice(item.getMenuItemId().getPrice());
			showOrderVO.setQuantity(item.getQuantity());
			showOrderVO.setTotalPrice(item.getMenuItemId().getPrice()*item.getQuantity());
			showOrderVO.setOverAllTotalPrice(foodOrder.getTotalPrice());
			showOrderVOList.add(showOrderVO);
			id++;
		}
		return showOrderVOList;
	}
	
	public FoodOrder getFoodOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}
	
	public List<FoodOrder> getAllFoodOrders(){
		List<FoodOrder> foodOrders = new ArrayList<>();
		List<OrderStatus> oStatusList = new ArrayList<>();
		oStatusList.add(OrderStatus.ACCEPTED);
		oStatusList.add(OrderStatus.COOKED);
		oStatusList.add(OrderStatus.IS_COOKING);
		orderRepository.findByOrderStatus(oStatusList, OrderType.HOME_DELIVERY).forEach(foodOrders::add);
		return foodOrders;
	}
}
