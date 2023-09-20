package com.example.productservice.services;

import java.util.List;

import com.example.productservice.dtos.GenericFakeStoreProductDto;
import com.example.productservice.exceptions.NotFoundException;

public interface ProductService {
    public GenericFakeStoreProductDto getProductById(long id) throws NotFoundException;

    public List<GenericFakeStoreProductDto> getAllProducts();

    public GenericFakeStoreProductDto createProduct(GenericFakeStoreProductDto product);

    // public void deleteProductById(long id);

    public GenericFakeStoreProductDto deleteProductById(long id);

    public GenericFakeStoreProductDto updateProductById(long id, GenericFakeStoreProductDto updatedProduct);
}
