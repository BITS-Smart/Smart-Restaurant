package com.bitssmart.smartRestaurant.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Model.Customer;
import com.bitssmart.smartRestaurant.Repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer saveCustomerDetails(Customer customer) {
		customer = customerRepository.save(customer);
		return customer;
	}
	
	public Customer findByPhoneNumber(Customer customer) {
		customer = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
		return customer;
	}

}
