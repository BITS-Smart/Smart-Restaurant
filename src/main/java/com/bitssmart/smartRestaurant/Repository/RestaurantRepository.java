package com.bitssmart.smartRestaurant.Repository;

import org.springframework.data.repository.CrudRepository;

import com.bitssmart.smartRestaurant.Model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long > {

}
