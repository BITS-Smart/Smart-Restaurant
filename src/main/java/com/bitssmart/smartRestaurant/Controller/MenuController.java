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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
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
    public ModelAndView manage_menu() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getName());
        modelAndView.setViewName("edit_menu");
        return modelAndView;
    }

//    @GetMapping("/menu_display")
//    public List<MenuItems> retrieveCompleteRestaurantMenu() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getName());
//        User user = userService.findUserByEmail(auth.getName());
//        Restaurant restaurant = restaurantService.getRestaurant(user.getRestaurantId().getId());
////        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
//        List<MenuItems> menuItems = restaurant.getMenuitems().stream().filter(MenuItems::getIsEnabled).collect(Collectors.toList());
//
//        return menuItems;
//
//    }

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


//    @GetMapping("/edit_menu_item")
//    public ModelAndView edit_menu() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("menu_item_edit");
//        return modelAndView;
//
//    }

    @GetMapping("/edit_menu_item/{id}")
    public String showMenuUpdateForm(@PathVariable("id") long id, Model model) {
        MenuItems menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item Id:" + id));

        model.addAttribute("menuItem", menuItem);
        return "updateMenuItem";
    }

    @PostMapping("/updateMenuItem/{id}")
    public String updateMenuItem(@PathVariable("id") long id, @Valid MenuItems menuItem,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            menuItem.setId(id);
            return "updateMenuItem";
        }
        MenuItems existing_menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item Id:" + id));
        existing_menuItem.setDescription(menuItem.getDescription());
        existing_menuItem.setFoodCategory(menuItem.getFoodCategory());
        existing_menuItem.setIngredients(menuItem.getIngredients());
        existing_menuItem.setIsVeg(menuItem.getIsVeg());
        existing_menuItem.setName(menuItem.getName());
        existing_menuItem.setPrice(menuItem.getPrice());

        menuItemRepository.save(existing_menuItem);
        return "redirect:/menuedit";
    }

}
