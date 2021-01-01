package com.bitssmart.smartRestaurant.Model;

import lombok.*;

import java.util.Date;

import javax.persistence.*;

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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date creatededAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "isEnabled", columnDefinition = "bit(1) default 1", nullable = false)
	private Boolean isEnabled;
}
