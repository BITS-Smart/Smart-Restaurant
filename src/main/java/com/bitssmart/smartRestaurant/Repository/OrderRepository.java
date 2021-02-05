package com.bitssmart.smartRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.OrderType;

public interface OrderRepository extends CrudRepository<FoodOrder, Long > {

	@Query("SELECT a FROM FoodOrder a WHERE orderStatus in (:orderStatus) AND orderType = :orderType")
	public List<FoodOrder> findByOrderStatus(@Param("orderStatus") List<OrderStatus> orderStatus, @Param("orderType") OrderType orderType);
}
