package com.example.dashboard.service;

import com.example.dashboard.domain.entity.Product;
import com.example.dashboard.domain.entity.User;
import com.example.dashboard.exceptions.LengthException;
import com.example.dashboard.exceptions.NotFoundException;
import com.example.dashboard.exceptions.ValidationException;
import com.example.dashboard.mapper.ProductMapper;
import com.example.dashboard.repository.ProductRepository;
import com.example.dashboard.repository.UserRepository;
import com.example.dashboard.validator.ProductValidator;
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

    @Autowired
    private ProductValidator productValidator;


    public Product create(RequestProduct requestProduct) {
        userRepository.findById(requestProduct.id_user())
                .orElseThrow(() -> new ValidationException("User not found."));

        productValidator.validateRequestProduct(requestProduct);

        return repository.save(new Product((requestProduct)));
    }

    public List<Product> search() {
        return repository.findAll();
    }

    public ResponseEntity<Product> searchById(UUID id) {
        return ResponseEntity.ok(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
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
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        return repository.findAll();
    }
}
