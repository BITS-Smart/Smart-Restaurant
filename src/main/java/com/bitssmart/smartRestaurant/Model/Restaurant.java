package com.bitssmart.smartRestaurant.Model;

import lombok.*;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "about")
	private String about;
	
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
	
	@ToString.Exclude
	@OneToMany(mappedBy = "restaurantId", cascade = CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private List<User> users;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "restaurantId", cascade = CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private List<Tables> tables;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "restaurantId", cascade = CascadeType.ALL)
	@JsonIgnore
	@PrimaryKeyJoinColumn
	private List<MenuItems> menuitems;
}
