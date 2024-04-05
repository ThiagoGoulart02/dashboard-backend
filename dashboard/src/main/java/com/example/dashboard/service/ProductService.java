package com.example.dashboard.service;

import com.example.dashboard.domain.entity.Product;
import com.example.dashboard.domain.entity.User;
import com.example.dashboard.exceptions.LengthException;
import com.example.dashboard.exceptions.ValidationException;
import com.example.dashboard.mapper.ProductMapper;
import com.example.dashboard.repository.ProductRepository;
import com.example.dashboard.repository.UserRepository;
import com.example.dashboard.web.representation.request.product.RequestProduct;
import com.example.dashboard.web.representation.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductMapper productMapper;


    public Product create(RequestProduct requestProduct) {
        if(requestProduct.title().length()>160) throw new LengthException("Invalid title. The title must be less then 160.");
        if(requestProduct.amount()<0) throw new ValidationException("Invalid amount. The amount must be positive.");
        if(requestProduct.price()<0) throw new ValidationException("Invalid price. The price must be positive.");
        if(requestProduct.description().length()>360) throw new LengthException("Invalid description. Description must be less than 360 characters.");
        return repository.save(new Product((requestProduct)));
    }

    public List<Product> search() {
        return repository.findAll();
    }

    public Optional<Product> searchById(UUID id) {
        return repository.findById(id);
    }

    public ResponseEntity<List<ProductsResponse>> searchByUser(UUID id) {
        return userRepository.findById(id)
                .map(user -> {
                    List<Product> products = repository.findByUserId(id);
                    ProductsResponse response = productMapper.toResponse(user, products);
                    return ResponseEntity.ok(Collections.singletonList(response));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public Product updateProduct(UUID id, Product product) {
        return repository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setTitle(product.getTitle());
                    existingProduct.setAmount(product.getAmount());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setDescription(product.getDescription());
                    return repository.save(existingProduct);
                })
                .orElse(null);
    }

    public List<Product> deleteProduct(UUID id) {
        repository.deleteById(id);
        return repository.findAll();
    }
}