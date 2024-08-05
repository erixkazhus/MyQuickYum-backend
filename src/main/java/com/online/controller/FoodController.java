package com.online.controller;

import com.online.model.Food;
import com.online.model.Restaurant;
import com.online.model.UserEnt;
import com.online.request.CreateFoodRequest;
import com.online.service.FoodService;
import com.online.service.RestaurantService;
import com.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name, @RequestHeader("Authorization") String jwt) throws Exception {

        UserEnt user = userService.findUserByJwtToken(jwt);
        List<Food> foods =foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam(required = false) String food_category, @RequestParam boolean vegetarian, @RequestParam boolean seasonal, @RequestParam boolean nonveg, @PathVariable Long restaurantId, @RequestHeader("Authorization") String jwt) throws Exception {

        UserEnt user = userService.findUserByJwtToken(jwt);
        List<Food> foods =foodService.getRestaurantsFood(restaurantId, vegetarian, nonveg, seasonal, food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
