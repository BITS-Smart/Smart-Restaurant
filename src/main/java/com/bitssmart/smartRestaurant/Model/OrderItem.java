package com.bitssmart.smartRestaurant.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "order_items")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "note", nullable = false)
	private String note;
	
	@Column(name = "orderStatus", nullable = false)
	private OrderStatus orderStatus;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private FoodOrder orderId;
	
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private MenuItems menuItemId;
	
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private User userId;
	
	@Column(name = "isCancelled", columnDefinition = "boolean default false", nullable = false)
	private Boolean isCancelled;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP )
	@Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
	private Date creatededAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
}
