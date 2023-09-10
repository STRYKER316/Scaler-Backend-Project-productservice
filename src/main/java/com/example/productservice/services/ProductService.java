package com.example.productservice.services;

import java.util.List;

import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;

public interface ProductService {
    public GenericProductDto getProductById(long id) throws NotFoundException;

    public List<GenericProductDto> getAllProducts();

    public GenericProductDto createProduct(GenericProductDto product);

    // public void deleteProductById(long id);

    public GenericProductDto deleteProductById(long id);

    public GenericProductDto updateProductById(long id,  GenericProductDto updatedProduct);
}
