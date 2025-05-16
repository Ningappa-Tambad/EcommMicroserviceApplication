//package com.springacademy.ecartmicroservicesapp.services;
//
//import com.springacademy.ecartmicroservicesapp.Dtos.OrderResponse;
//import com.springacademy.ecartmicroservicesapp.model.CartItem;
//import com.springacademy.ecartmicroservicesapp.model.User;
//import com.springacademy.ecartmicroservicesapp.repository.CartItemRepository;
//import com.springacademy.ecartmicroservicesapp.repository.ProductRepository;
//import com.springacademy.ecartmicroservicesapp.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CartService {
//
//   private final ProductRepository productRepository;
//    private final UserRepository userRepository;
//    private final CartItemRepository cartItemRepository;
//
//    public CartService(UserRepository userRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
//        this.userRepository = userRepository;
//        this.cartItemRepository = cartItemRepository;
//        this.productRepository = productRepository;
//    }
//
//    public List<CartItem> getCart(String userId) {
//
//        // Logic to retrieve the cart items for the user
//        // This is just a placeholder implementation
//        return cartItemRepository.findByUser((User) userRepository.findById(Long.parseLong(userId))
//                .map(cartItemRepository::findByUser)
//                .orElseGet(() -> List.of()));
//
//
//    }
//
//    public void clearCart(String userId) {
//        // Logic to clear the cart for the user
//        // This is just a placeholder implementation
//               userRepository.findById(Long.parseLong(userId))
//                       .ifPresent(cartItemRepository::deleteByUser);
//
//    }
//
//
//
//}
