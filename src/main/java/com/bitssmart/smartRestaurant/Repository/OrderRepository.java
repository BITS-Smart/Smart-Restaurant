package com.bitssmart.smartRestaurant.Repository;

import org.springframework.data.repository.CrudRepository;

import com.bitssmart.smartRestaurant.Model.FoodOrder;

public interface OrderRepository extends CrudRepository<FoodOrder, Long > {

}
