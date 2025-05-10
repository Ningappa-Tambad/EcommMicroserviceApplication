package com.springacademy.ecartmicroservicesapp.controller;


import com.springacademy.ecartmicroservicesapp.Dtos.ProductRequest;
import com.springacademy.ecartmicroservicesapp.Dtos.ProductResponse;
import com.springacademy.ecartmicroservicesapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController
{

    private ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest)
    {
        // Call the service to create the product
        // productService.createProduct(productRequest);

        // Return a response indicating success
        ProductResponse productResponse = productService.CreateProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

}
