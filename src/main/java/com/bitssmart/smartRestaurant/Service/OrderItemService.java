package com.bitssmart.smartRestaurant.Service;

import java.util.List;

import com.bitssmart.smartRestaurant.Bean.OrderItemBean;
import com.bitssmart.smartRestaurant.Bean.OrderItemBeanforChef;
import com.bitssmart.smartRestaurant.Model.OrderItem;
import com.bitssmart.smartRestaurant.Model.OrderStatus;

public interface OrderItemService {
	
	List<OrderItemBean> getTopFiveOrderItems();
	List<OrderItemBeanforChef> getPendingItemsForChef();
	OrderItemBeanforChef getOrderItemById(long id);
	OrderItem updateOrderItem(OrderStatus orderstatus, long id);
	
	OrderItem showFoodOrderItem(long foodOrderId);
	OrderItem saveFoodOrder(OrderItem orderItem);

}
