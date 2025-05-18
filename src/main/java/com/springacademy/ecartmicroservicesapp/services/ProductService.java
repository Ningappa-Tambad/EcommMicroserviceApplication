package com.springacademy.ecartmicroservicesapp.services;

import com.springacademy.ecartmicroservicesapp.Dtos.ProductRequest;
import com.springacademy.ecartmicroservicesapp.Dtos.ProductResponse;
import com.springacademy.ecartmicroservicesapp.model.Product;
import com.springacademy.ecartmicroservicesapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

@Autowired
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


    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {

        // Logic to update a product
      return productRepository.findById(id)
                .map(existingProduct ->
                {
                 convertProductRequestToProduct(existingProduct, productRequest);
                    Product savedProduct = productRepository.save(existingProduct);
                    return mapToProductResponse(savedProduct);

                });
    }

    public Optional<ProductResponse> fetchSingleProduct(Long id) {

        // Logic to fetch a single product by ID
        return productRepository.findById(id)
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(product.getId());
                    productResponse.setName(product.getName());
                    productResponse.setDescription(product.getDescription());
                    productResponse.setPrice(product.getPrice());
                    productResponse.setStockQuantity(product.getStockQuantity());
                    productResponse.setCategory(product.getCategory());
                    productResponse.setImageUrl(product.getImageUrl());
                    productResponse.setActive(product.getActive());
                    return Optional.of(productResponse);
                })
                .orElse(Optional.empty());
    }

    public List<ProductResponse> fetchAllProducts() {
        // Logic to fetch all products
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(product.getId());
                    productResponse.setName(product.getName());
                    productResponse.setDescription(product.getDescription());
                    productResponse.setPrice(product.getPrice());
                    productResponse.setStockQuantity(product.getStockQuantity());
                    productResponse.setCategory(product.getCategory());
                    productResponse.setImageUrl(product.getImageUrl());
                    productResponse.setActive(product.getActive());
                    return productResponse;
                })
                .toList();
    }

    public void deleteProductById(Long id) {

        // Logic to delete a product by ID
        productRepository.deleteById(id);
    }

    public void deleteAllProducts() {

        // Logic to delete all products
        productRepository.deleteAll();
    }

    public List<ProductResponse> searchProducts(String name)
    {
        // Logic to search products by name
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }
}
