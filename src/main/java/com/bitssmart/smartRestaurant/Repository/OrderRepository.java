package com.bitssmart.smartRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bitssmart.smartRestaurant.Model.Customer;
import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.OrderStatus;
import com.bitssmart.smartRestaurant.Model.OrderType;
import com.bitssmart.smartRestaurant.Model.UserRoles;

public interface OrderRepository extends CrudRepository<FoodOrder, Long > {

	@Query("SELECT a FROM FoodOrder a WHERE orderStatus in (:orderStatus) AND orderType = :orderType")
	public List<FoodOrder> findByOrderStatus(@Param("orderStatus") List<OrderStatus> orderStatus, @Param("orderType") OrderType orderType);

	@Query("SELECT a FROM FoodOrder a WHERE customerID.id =:custid")
	public List<FoodOrder> findByCustomerId(@Param("custid") Long id);
	
	@Query("SELECT a FROM FoodOrder a WHERE userId.id =:delId and userId.userRoles=:delivery_guy")
	public List<FoodOrder> findByDeliveryGuy(@Param("delId") Long id,@Param("delivery_guy") UserRoles userRoles);
	
	/*
	 * @Query("SELECT a FROM FoodOrder a WHERE a.orderItems[0].menuItemId.restaurantId.id= :resId "
	 * ) public List<FoodOrder> findByRestaurantId(@Param("resId") Long id);
	 */
	@Query("SELECT a FROM FoodOrder a")
	public List<FoodOrder> findByRestaurantId();
}
