package com.example.dashboard.mapper;

import com.example.dashboard.domain.entity.Product;
import com.example.dashboard.domain.entity.User;
import com.example.dashboard.web.representation.response.ProductsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductsResponse toResponse(User user, List<Product> products){
        return new ProductsResponse(
                user.getId_user(),
                user.getEmail(),
                user.getPassword(),
                user.getCompany_name(),
                products
        );
    }
}
