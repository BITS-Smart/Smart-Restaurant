package com.bitssmart.smartRestaurant.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "food_order")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP )
	@Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
	private Date creatededAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "totalPrice", nullable = false)
	private float totalPrice;
	
	@Column(name = "isPaid", columnDefinition = "boolean default false", nullable = false)
	private Boolean isPaid;
	
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private Tables tableId;
	
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private User userId;
	
	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	@ToString.Exclude
	private List<OrderItem> orderItems;
	
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private Customer customerID;
	
	@OneToOne(mappedBy = "foodOrder", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Payment payment;
	
}
