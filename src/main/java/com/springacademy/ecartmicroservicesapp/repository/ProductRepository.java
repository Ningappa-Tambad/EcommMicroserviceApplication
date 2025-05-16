package com.springacademy.ecartmicroservicesapp.repository;

import com.springacademy.ecartmicroservicesapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM products p WHERE p.name LIKE %?1% and p.stockQuantity>0 AND p.active=true AND p.price>0 AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByNameContainingIgnoreCase(@Param("name") String name);
    // Implement create, update, replace and delete methods



    Product save(Product product);

    @Override
    Optional<Product> findById(Long aLong);
}
