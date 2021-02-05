package com.bitssmart.smartRestaurant.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "users")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@ToString.Exclude
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private Restaurant restaurantId;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP )
	@Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
	private Date creatededAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "isEnabled", columnDefinition = "boolean default true", nullable = false)
	private Boolean isEnabled;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	@ToString.Exclude
	private List<Tables> tables;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	@ToString.Exclude
	private List<FoodOrder> orders;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	@ToString.Exclude
	private List<OrderItem> orderItems;
	
	@Column(name = "userRoles", nullable = false)
	private UserRoles userRoles;
	
	@ToString.Exclude
	@OneToOne(mappedBy = "userid", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;
	
	@ToString.Exclude
	@OneToOne(mappedBy = "userid", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private DeliveryGuy deliveryGuy;

}
