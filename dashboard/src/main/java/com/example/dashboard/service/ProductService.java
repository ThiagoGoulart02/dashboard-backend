package com.example.dashboard.service;

import com.example.dashboard.domain.entity.Product;
import com.example.dashboard.domain.entity.User;
import com.example.dashboard.mapper.ProductMapper;
import com.example.dashboard.repository.ProductRepository;
import com.example.dashboard.repository.UserRepository;
import com.example.dashboard.web.representation.request.product.RequestProduct;
import com.example.dashboard.web.representation.response.ProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductMapper productMapper;


    public Product create(RequestProduct requestProduct) {

        Product newProduct = new Product((requestProduct));
        repository.save(newProduct);
        return newProduct;
    }

    public List<Product> search() {
        return repository.findAll();
    }

    public Optional<Product> searchById(UUID id) {
        return repository.findById(id);
    }

    public ResponseEntity<List<ProductsResponse>> searchByUser(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Product> products = repository.findByUserId(id);
            ProductsResponse response = productMapper.toResponse(user, products);
            List<ProductsResponse> responseList = new ArrayList<>();
            responseList.add(response);
            return ResponseEntity.ok(responseList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Product> deleteProduct(UUID id){
        repository.deleteById(id);
        return repository.findAll();
    }
}
