package com.example.productservice.services;

import com.example.productservice.dtos.GenericDbProductDto;
import com.example.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface DbProductService {
    public GenericDbProductDto getProductById(UUID id) throws NotFoundException;

    public List<GenericDbProductDto> getAllProducts();

    public Boolean createProduct(GenericDbProductDto product);

    // public void deleteProductById(long id);

    public GenericDbProductDto deleteProductById(UUID id);

    public GenericDbProductDto updateProductById(UUID id, GenericDbProductDto updatedProduct);
}
