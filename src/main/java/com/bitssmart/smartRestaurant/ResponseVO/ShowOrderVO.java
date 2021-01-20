package com.bitssmart.smartRestaurant.ResponseVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowOrderVO {

	private int id;
	
	private String menuItemName;
	
	private int quantity;
	
	private float price;
	
	private float totalPrice;
	
	private float overAllTotalPrice;
}
