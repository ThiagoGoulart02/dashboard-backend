package com.example.dashboard.web.representation.response;

import com.example.dashboard.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponse {

    private UUID id_user;
    private String email;
    private String password;
    private String company_name;
    private List<Product> products;
}
