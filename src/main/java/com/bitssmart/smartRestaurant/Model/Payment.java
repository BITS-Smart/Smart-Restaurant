package com.bitssmart.smartRestaurant.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "payment")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "amount", nullable = false)
	private float amount;
	
	@Column(name = "paymentOptions", nullable = false)
	private PaymentOptions paymentOptions;
	
	@OneToOne
	@MapsId
    @JoinColumn(name = "order_id")
    private FoodOrder foodOrder;
	
}
