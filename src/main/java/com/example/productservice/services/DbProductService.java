package com.example.productservice.services;

import com.example.productservice.dtos.DbProductDto;
import com.example.productservice.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface DbProductService {
    public DbProductDto getProductById(UUID id) throws NotFoundException;

    public List<DbProductDto> getAllProducts();

    public Boolean createProduct(DbProductDto product);

    // public void deleteProductById(long id);

    public void deleteProductById(UUID id);

    public DbProductDto updateProductById(UUID id, DbProductDto updatedProduct);
}
