package com.example.dashboard.domain.entity;

import com.example.dashboard.web.representation.request.product.RequestProduct;
import jakarta.persistence.*;
import lombok.*;
import org.apache.coyote.Request;

import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_product;
    @Column(name = "id_user")
    private UUID userId;
    private String title;
    private int amount;
    private double price;
    private String description;

    public Product(RequestProduct requestProduct) {
        this.userId = requestProduct.id_user();
        this.title = requestProduct.title();
        this.amount = requestProduct.amount();
        this.price = requestProduct.price();
        this.description = requestProduct.description();
    }
}
