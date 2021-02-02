package com.bitssmart.smartRestaurant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Model.Payment;
import com.bitssmart.smartRestaurant.Repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment savePaymentDetails(Payment payment) {
		payment = paymentRepository.save(payment);
		return payment;
	}
}
