package com.example.productservice.services;

import org.springframework.stereotype.Service;

import com.example.productservice.models.Product;


@Service("selfProductService")
public class SelfProductService implements ProductService{

    @Override
    public String getProductById(long id) {
        return null;
    }
    
}
