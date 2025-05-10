package com.springacademy.ecartmicroservicesapp.services;

import com.springacademy.ecartmicroservicesapp.Dtos.ProductRequest;
import com.springacademy.ecartmicroservicesapp.Dtos.ProductResponse;
import com.springacademy.ecartmicroservicesapp.model.Product;
import com.springacademy.ecartmicroservicesapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private  ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public  ProductResponse CreateProduct(ProductRequest productRequest) {
        // Logic to create a product
        Product product=new Product();
        convertProductRequestToProduct(product,productRequest);
        Product savedProduct = productRepository.save(product);
       return mapToProductResponse(savedProduct);
    }

    private  ProductResponse mapToProductResponse(Product savedProduct) {


        ProductResponse productResponse=new ProductResponse();
    productResponse.setId(savedProduct.getId());
    productResponse.setName(savedProduct.getName());
    productResponse.setDescription(savedProduct.getDescription());
    productResponse.setPrice(savedProduct.getPrice());
    productResponse.setStockQuantity(savedProduct.getStockQuantity());
    productResponse.setCategory(savedProduct.getCategory());
    productResponse.setImageUrl(savedProduct.getImageUrl());
    productResponse.setActive(savedProduct.getActive());
        return productResponse;
    }

    private void convertProductRequestToProduct(Product product, ProductRequest productRequest) {

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
    }


}
