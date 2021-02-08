package com.bitssmart.smartRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bitssmart.smartRestaurant.Bean.OrderItemBean;
import com.bitssmart.smartRestaurant.Bean.OrderItemBeanforChef;
import com.bitssmart.smartRestaurant.Model.OrderItem;
import com.bitssmart.smartRestaurant.Model.OrderStatus;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
	
	@Query("select new com.bitssmart.smartRestaurant.Bean.OrderItemBean("
			+ "count(oi.menuItemId),mi.name,mi.price) from OrderItem oi,MenuItems mi "
			+ "where oi.menuItemId = mi.id  group by mi.name,mi.price,oi.menuItemId order by count(oi.menuItemId) desc"
			)
	List<OrderItemBean> findFirst5ByMenuItemId();
	
	@Query("select new com.bitssmart.smartRestaurant.Bean.OrderItemBeanforChef("
			+ "oi.id, oi.orderId , oi.orderStatus ,oi.note, oi.quantity , mi.name) "
			+ "from OrderItem oi , MenuItems mi " 
			+ "where oi.menuItemId = mi.id and oi.isCancelled = false and oi.orderStatus in (:orderStatus)"
			+ "order by oi.id")
	List<OrderItemBeanforChef> getPendingOrders(@Param("orderStatus") List<OrderStatus> orderStatus);
	
	@Query("select new com.bitssmart.smartRestaurant.Bean.OrderItemBeanforChef("
			+ "oi.id, oi.orderId , oi.orderStatus ,oi.note, oi.quantity , mi.name) "
			+ "from OrderItem oi , MenuItems mi " 
			+ "where oi.menuItemId = mi.id and oi.id in (:id)")
	OrderItemBeanforChef getOrderItemById(@Param("id") Long id);
	
	@Query("update OrderItem set order_status  = (:orderStatus) where id = (:id)")
	OrderItem updateOrderItem(@Param("orderStatus") OrderStatus orderStatus,@Param("id") Long id);

}
