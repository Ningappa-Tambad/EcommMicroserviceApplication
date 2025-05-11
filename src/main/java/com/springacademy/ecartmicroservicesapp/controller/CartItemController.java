package com.springacademy.ecartmicroservicesapp.controller;


import com.springacademy.ecartmicroservicesapp.Dtos.CartItemRequest;
import com.springacademy.ecartmicroservicesapp.services.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    private  final CartItemService cartService;

    public CartItemController(CartItemService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestHeader("X-User-Id") String userId,
                                                @RequestBody CartItemRequest request) {

        if(!cartService.addItemToCart(userId, request))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add item to cart OR User not found or product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
