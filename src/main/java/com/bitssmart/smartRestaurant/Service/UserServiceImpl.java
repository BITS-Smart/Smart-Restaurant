package com.bitssmart.smartRestaurant.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.Repository.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;

	 
	 @Override
	 public User findUserByEmail(String email) {
	  return userRepository.findByEmail(email);
	 }

	 @Override
	 public void saveUser(User user) {
	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	  userRepository.save(user);
	 }
	 
	 @Override
	 public List<User> findByDeliveryGuy() {
		  return userRepository.findByDeliveryGuy(false);
		 }

}
