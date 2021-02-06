package com.bitssmart.smartRestaurant.Controller;

import com.bitssmart.smartRestaurant.Model.FoodOrder;
import com.bitssmart.smartRestaurant.Model.MenuItems;
import com.bitssmart.smartRestaurant.Model.Restaurant;
import com.bitssmart.smartRestaurant.Model.User;
import com.bitssmart.smartRestaurant.Repository.MenuItemRepository;
import com.bitssmart.smartRestaurant.Service.MenuService;
import com.bitssmart.smartRestaurant.Service.RestaurantService;
import com.bitssmart.smartRestaurant.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @RequestMapping(value={"/menuedit"}, method = RequestMethod.GET)
    public ModelAndView edit_menu() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        modelAndView.setViewName("edit_menu");
        return modelAndView;
    }

    @GetMapping("/menu_display")
    public List<MenuItems> retrieveCompleteRestaurantMenu() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User user = userService.findUserByEmail(auth.getName());
        Restaurant restaurant = restaurantService.getRestaurant(user.getRestaurantId().getId());
//        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        List<MenuItems> menuItems = restaurant.getMenuitems().stream().filter(MenuItems::getIsEnabled).collect(Collectors.toList());

        return menuItems;

    }

    @PostMapping("/add_menu_item")
    public ModelAndView add_menu_item(MenuItems menuItems) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User user = userService.findUserByEmail(auth.getName());
        menuItems.setRestaurantId(user.getRestaurantId());  //
        menuItems.setIsEnabled(true);
        menuItems = menuService.saveMenuItem(menuItems);

        return new ModelAndView("redirect:/menuedit");
    }

    @PostMapping("/delete_menu_item")
    public HashMap<String, String> remove_menu_item(Long menu_item_id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getName());
        User user = userService.findUserByEmail(auth.getName());
        MenuItems menuItem = menuService.getMenuItem(menu_item_id);
        menuItem.setIsEnabled(false);
        menuItem = menuService.saveMenuItem(menuItem);

        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "Y");
        map.put("id", String.valueOf(menu_item_id));
        return map;
    }

}
