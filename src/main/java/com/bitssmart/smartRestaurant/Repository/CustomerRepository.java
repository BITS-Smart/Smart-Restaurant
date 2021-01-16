package com.bitssmart.smartRestaurant.Repository;

import org.springframework.data.repository.CrudRepository;

import com.bitssmart.smartRestaurant.Model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long > {
	
	public Customer findByPhoneNumber(String phoneNumber);

}
