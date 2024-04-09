package com.example.dashboard.controller.user;

import com.example.dashboard.domain.entity.Product;
import com.example.dashboard.service.ProductService;
import com.example.dashboard.web.representation.request.product.RequestProduct;
import com.example.dashboard.web.representation.response.ProductsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.search();
    }

    @GetMapping("/by-user/{id}")
    public ResponseEntity<List<ProductsResponse>> searchByUser(@PathVariable UUID id) {
        return productService.searchByUser(id);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Product> searchById(@PathVariable UUID id) {
        return productService.searchById(id);
    }

    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody @Valid RequestProduct data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(data));
    }

    @DeleteMapping("/delete/{id}")
    public List<Product> deleteProduct(@PathVariable UUID id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable UUID id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }
}
