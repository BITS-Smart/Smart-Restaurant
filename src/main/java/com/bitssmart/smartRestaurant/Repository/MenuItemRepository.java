package com.bitssmart.smartRestaurant.Repository;

import com.bitssmart.smartRestaurant.Bean.OrderItemBean;
import com.bitssmart.smartRestaurant.Model.MenuItems;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItems, Long> {
    
}
