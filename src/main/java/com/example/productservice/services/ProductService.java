package com.example.productservice.services;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.models.Product;

public interface ProductService {
    public GenericProductDto getProductById(long id);

    public GenericProductDto createProduct(GenericProductDto product);
}


