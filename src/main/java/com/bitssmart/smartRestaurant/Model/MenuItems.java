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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
@Table(name = "menu_items")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class MenuItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "ingredients")
	private String ingredients;
	
	@Column(name = "price", nullable = false)
	private float price;
	
	@Column(name = "foodCategory", nullable = false)
	private FoodCategory foodCategory;
	
	@Column(name = "isVeg", columnDefinition = "boolean default true", nullable = false)
	private Boolean isVeg;
	
	@Temporal(TemporalType.TIMESTAMP )
	@CreationTimestamp
	@Column(name = "created_at")
	private Date creatededAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "isEnabled", columnDefinition = "boolean default true", nullable = false)
	private Boolean isEnabled;
	
	@ManyToOne
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private Restaurant restaurantId;
	
	@OneToMany(mappedBy = "menuItemId", cascade = CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private List<OrderItem> orderItems;
}
