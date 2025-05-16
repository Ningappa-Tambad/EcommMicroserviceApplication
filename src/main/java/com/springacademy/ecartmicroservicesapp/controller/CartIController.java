package com.springacademy.ecartmicroservicesapp.controller;


import com.springacademy.ecartmicroservicesapp.Dtos.CartItemRequest;
import com.springacademy.ecartmicroservicesapp.model.CartItem;
import com.springacademy.ecartmicroservicesapp.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartIController {

    private  final CartService cartService;

    public CartIController(CartService cartService) {
        this.cartService = cartService;
    }


    //Add product to cart


    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestHeader("X-User-Id") String userId,
                                                @RequestBody CartItemRequest request) {

        if(!cartService.addToCart(userId, request))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add item to cart OR User not found or product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> deleteItemFromCart(@RequestHeader("X-User-Id") String userId,
                                                     @PathVariable Long productId) {

        boolean deleted=cartService.deleteItemFromCart(userId, productId);
     return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> getCart(@RequestHeader("X-User-Id") String userId) {
        // Logic to retrieve cart items for the user
      return ResponseEntity.ok(cartService.getCart(userId));
    }


}
