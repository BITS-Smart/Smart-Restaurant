package com.bitssmart.smartRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bitssmart.smartRestaurant.Bean.OrderItemBean;
import com.bitssmart.smartRestaurant.Model.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
	
	@Query("select new com.bitssmart.smartRestaurant.Bean.OrderItemBean("
			+ "count(oi.menuItemId),mi.name,mi.price) from OrderItem oi,MenuItems mi "
			+ "where oi.menuItemId = mi.id  group by mi.name,mi.price,oi.menuItemId order by count(oi.menuItemId) desc"
			)
	List<OrderItemBean> findFirst5ByMenuItemId();

}
