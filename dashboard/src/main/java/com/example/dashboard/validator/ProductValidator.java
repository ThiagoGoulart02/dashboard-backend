package com.example.dashboard.validator;

import com.example.dashboard.exceptions.LengthException;
import com.example.dashboard.exceptions.ValidationException;
import com.example.dashboard.web.representation.request.product.RequestProduct;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public final int TITLE_MAX_LENGTH = 160;
    public final int DESCRIPTION_MAX_LENGTH = 360;

    public void validateRequestProduct(RequestProduct requestProduct){
        if(requestProduct.title().length()>TITLE_MAX_LENGTH) throw new LengthException("Invalid title. The title must be less then 160.");
        if(requestProduct.amount()<0) throw new ValidationException("Invalid amount. The amount must be positive.");
        if(requestProduct.price()<0) throw new ValidationException("Invalid price. The price must be positive.");
        if(requestProduct.description().length()>DESCRIPTION_MAX_LENGTH) throw new LengthException("Invalid description. Description must be less than 360 characters.");
    }
}
