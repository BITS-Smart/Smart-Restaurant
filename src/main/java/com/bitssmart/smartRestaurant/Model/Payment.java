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
	
	@Column(name = "paymentId")
	private String paymentId;
	

	@Column(name = "paymentMode")
	private String paymentMode;
	
	@Column(name = "bankName")
	private String bankName;
	
	@Column(name = "bankTXNID")
	private String bankTXNID;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "gatewayName")
	private String gatewayName;
	
	@Column(name = "respCode")
	private String respCode;
	
	@Column(name = "repMsg")
	private String repMsg;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "txnAmount")
	private String txnAmount;
	
	@Column(name = "txnDate")
	private String txnDate;
	

	
  
	@ToString.Exclude
	@OneToOne
	@MapsId
    @JoinColumn(name = "order_id")
    private FoodOrder foodOrder;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP )
	@Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
	private Date creatededAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;
	
}
