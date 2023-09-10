package com.example.productservice.services;

import java.util.List;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.models.Product;

public interface ProductService {
    public GenericProductDto getProductById(long id);

    public GenericProductDto createProduct(GenericProductDto product);

    public List<GenericProductDto> getAllProducts();

    // public void deleteProductById(long id);

    public GenericProductDto deleteProductById(long id);

    public GenericProductDto updateProductById(long id);
}


