package com.bitssmart.smartRestaurant.Repository;

import org.springframework.data.repository.CrudRepository;

import com.bitssmart.smartRestaurant.Model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long > {

}
