package com.example.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.productservice.dtos.GenericProductDto;


@Service("selfProductService")
public class SelfProductService implements ProductService{

    @Override
    public GenericProductDto getProductById(long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(long id) {
        return null;
    }


    // @Override
    // public void deleteProductById(long id) {
        
    // }


    @Override
    public GenericProductDto updateProductById(long id, GenericProductDto updatedProduct) {
        return null;
    }
    
}
