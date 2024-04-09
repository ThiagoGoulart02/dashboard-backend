package com.example.dashboard.repository;

import com.example.dashboard.domain.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByUserId(UUID userId);

    @Transactional
    void deleteByUserId(UUID userId);
}

