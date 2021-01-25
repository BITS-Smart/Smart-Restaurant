package com.bitssmart.smartRestaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Model.DeliveryGuy;

import com.bitssmart.smartRestaurant.Repository.DeliveryGuyRepository;


@Service("deliveryGuyService")
public class DeliveryGuyServiceImpl implements DeliveryGuyService {

	@Autowired
	 private DeliveryGuyRepository guyRepository;
	 
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;

	 
	 @Override
	 public DeliveryGuy findUserByEmail(String email) {
	  return guyRepository.findByEmail(email);
	 }

	 @Override
	 public void saveUser(DeliveryGuy guy) {
		 guy.setPassword(bCryptPasswordEncoder.encode(guy.getPassword()));
		 guyRepository.save(guy);
	 }
}

