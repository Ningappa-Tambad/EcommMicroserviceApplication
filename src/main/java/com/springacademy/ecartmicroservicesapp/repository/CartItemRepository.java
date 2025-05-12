package com.springacademy.ecartmicroservicesapp.repository;

import com.springacademy.ecartmicroservicesapp.model.CartItem;
import com.springacademy.ecartmicroservicesapp.model.Product;
import com.springacademy.ecartmicroservicesapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find cart items by user ID or product ID


  //Test

    CartItem findByUserAndProduct(User user, Product product);
}