package com.bitssmart.smartRestaurant.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Model.Restaurant;
import com.bitssmart.smartRestaurant.Repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public List<Restaurant> getAllRestaurants(){
		List<Restaurant> restaurants = new ArrayList<>();
		restaurantRepository.findAll().forEach(restaurants::add);
		return restaurants;
	}
	
	public Restaurant getRestaurant(Long id) {
		return restaurantRepository.findById(id).orElse(null);
	}
}
