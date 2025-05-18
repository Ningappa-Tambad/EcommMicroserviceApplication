package com.springacademy.ecartmicroservicesapp.services;

import com.springacademy.ecartmicroservicesapp.Dtos.CartItemRequest;
import com.springacademy.ecartmicroservicesapp.model.CartItem;
import com.springacademy.ecartmicroservicesapp.model.Product;
import com.springacademy.ecartmicroservicesapp.model.User;
import com.springacademy.ecartmicroservicesapp.repository.CartItemRepository;
import com.springacademy.ecartmicroservicesapp.repository.ProductRepository;
import com.springacademy.ecartmicroservicesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(ProductRepository productRepository, UserRepository userRepository, CartItemRepository cartItemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public boolean addToCart(String userId, CartItemRequest request)
    {
        // Logic to add item to cart
        // This could involve checking if the product exists, updating the cart, etc.
        // For now, we'll just print the details

     Optional<Product>  productOpt= productRepository.findById(request.getProductId());
        if(productOpt.isEmpty())
        {
            throw new RuntimeException("Product not found");
        }
        Product product=productOpt.get();
        if(product.getStockQuantity()<= request.getQuantity())
        {
            throw new RuntimeException("Product out of stock");
        }
        if(!userRepository.existsById(Long.parseLong(userId)))
        {
            throw new RuntimeException("User not found");
        }

        Optional<User> userOpt=userRepository.findById(Long.parseLong(userId));
        if(userOpt.isEmpty())
        {
            throw new RuntimeException("User not found");
        }
        User user=userOpt.get();

        CartItem existingItem= cartItemRepository.findByUserAndProduct(user,product);

        if(existingItem!=null)
        {

            // Check if the product is already in the cart and update the quantity

            existingItem.setQuantity(existingItem.getQuantity()+request.getQuantity());
            existingItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingItem.getQuantity())));
            cartItemRepository.save(existingItem);
        }
        else
        {
            // If the product is not in the cart, create a new CartItem object and save it
            CartItem cartItem=new CartItem();
            cartItem.setProduct(product);
            cartItem.setUser(user);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        // Update the product stock quantity
   return true;
    }

    public boolean  deleteItemFromCart(String userId, Long productId) {
        // Logic to delete item from cart
        // This could involve checking if the product exists, updating the cart, etc.
        // For now, we'll just print the details

        Optional<Product>  productOpt= productRepository.findById(productId);
        Optional<User> userOpt=userRepository.findById(Long.parseLong(userId));

        if(productOpt.isEmpty())
        {
            throw new RuntimeException("Product not found");
        }
        if(userOpt.isEmpty())
        {
            throw new RuntimeException("User not found");
        }
        User user=userOpt.get();
        Product product=productOpt.get();
        CartItem existingItem= cartItemRepository.findByUserAndProduct(user,product);
        if(existingItem==null)
        {
            throw new RuntimeException("Item not found in cart");
        }
        cartItemRepository.deleteByUserAndProduct(user,product);
        return true;

    }

//Clear cart

    public void clearCart(String userId) {
       // Logic to clear the cart for the user
       // This is just a placeholder implementation
              userRepository.findById(Long.parseLong(userId))
                      .ifPresent(cartItemRepository::deleteByUser);

    }

    public List<CartItem> getCart(String userId) {

        // Logic to retrieve cart items for the user
        // For now, we'll just return a placeholder response
        return userRepository.findById(Long.parseLong(userId))
                .map(cartItemRepository::findByUser)
                .orElseGet(List::of);

    }
}
