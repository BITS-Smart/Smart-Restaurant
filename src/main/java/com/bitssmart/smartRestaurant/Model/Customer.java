package com.bitssmart.smartRestaurant.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
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
@Table(name = "customer")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name = "name", nullable = false)
//	private String name;
	
	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;
	
//	@Column(name = "email", nullable = false)
//	private String email;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP )
	@Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
	private Date creatededAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "isVIP", columnDefinition = "boolean default false", nullable = false)
	private Boolean isVIP;
	
	@Column(name = "address")
	private String address;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "customerID", cascade = CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private List<FoodOrder> orderId;

	@ToString.Exclude
	@OneToOne
	@MapsId
    @JoinColumn(name = "user_id")
    private User userid;
}
