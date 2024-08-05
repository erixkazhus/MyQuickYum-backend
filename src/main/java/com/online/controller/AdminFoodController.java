package com.online.controller;

import com.online.model.Food;
import com.online.model.Restaurant;
import com.online.model.UserEnt;
import com.online.request.CreateFoodRequest;
import com.online.request.CreateRestaurantRequest;
import com.online.response.MessageResponse;
import com.online.service.FoodService;
import com.online.service.RestaurantService;
import com.online.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception {

        UserEnt user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant =restaurantService.findRestaurantById(req.getRestaurantId());
        Food food =foodService.createFood(req, req.getCategory(), restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        UserEnt user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("Food deleted");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {

        UserEnt user = userService.findUserByJwtToken(jwt);
        Food food =foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
