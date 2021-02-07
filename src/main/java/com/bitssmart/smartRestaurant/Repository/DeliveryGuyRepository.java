package com.bitssmart.smartRestaurant.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bitssmart.smartRestaurant.Model.DeliveryGuy;



@Repository("deliveryGuyRepository")
public interface DeliveryGuyRepository extends JpaRepository<DeliveryGuy, Long> {

	//DeliveryGuy findByEmail(String loginId);
}
