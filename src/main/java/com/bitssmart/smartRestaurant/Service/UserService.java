package com.bitssmart.smartRestaurant.Service;

import java.util.List;

import com.bitssmart.smartRestaurant.Model.User;

public interface UserService {
	 public User findUserByEmail(String email);
	 
	 public void saveUser(User user);
	 
	 public List<User> findByDeliveryGuy();
}
