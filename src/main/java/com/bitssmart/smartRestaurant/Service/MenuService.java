package com.bitssmart.smartRestaurant.Service;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.MenuItems;
import com.bitssmart.smartRestaurant.Repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItems saveMenuItem(MenuItems menuItems) {
        menuItems = menuItemRepository.save(menuItems);
        return menuItems;
    }

    public MenuItems getMenuItem(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }


}
