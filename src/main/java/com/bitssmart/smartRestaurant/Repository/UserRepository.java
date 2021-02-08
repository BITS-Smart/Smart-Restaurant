package com.bitssmart.smartRestaurant.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	 User findByEmail(String loginId);
	 
	 @Query("SELECT a FROM User a WHERE deliveryGuy.isApproved= :boolVal")
	 public List<User> findByDeliveryGuy(@Param("boolVal") Boolean val);
}
