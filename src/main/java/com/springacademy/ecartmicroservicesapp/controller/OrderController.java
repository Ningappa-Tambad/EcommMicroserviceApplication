package com.springacademy.ecartmicroservicesapp.controller;

import com.springacademy.ecartmicroservicesapp.Dtos.OrderResponse;
import com.springacademy.ecartmicroservicesapp.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")

public class OrderController

{
    // Inject the OrderService here
     private final  OrderService orderService;

     @Autowired
     public OrderController(OrderService orderService) {
         this.orderService = orderService;

     }

    // Define endpoints for creating, updating, and retrieving orders here
    // For example:

     @PostMapping(value = "/orders", produces = "application/json")

     public ResponseEntity<OrderResponse> createOrder(
             @RequestHeader ("X-User-Id") String userId) {
         return orderService.createOrder(userId)
                .map(orderResponse -> new ResponseEntity<>(orderResponse, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
     }




}
