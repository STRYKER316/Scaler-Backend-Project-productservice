package com.example.productservice.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.productservice.dtos.ExceptionDto;
import com.example.productservice.dtos.GenericDbProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.productservice.dtos.GenericFakeStoreProductDto;


@Primary
@Service("selfProductService")
public class SelfProductService implements DbProductService{

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Helper Method
    private GenericDbProductDto covertProductToGenericDbProductDto(Product product) {
        GenericDbProductDto genericProductDto = new GenericDbProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice().getPrice());

        return genericProductDto;
    }

    @Override
    public GenericDbProductDto getProductById(UUID id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return covertProductToGenericDbProductDto(product.get());
        } else throw new NotFoundException("The product with id: " + id + " is not found");

    }

    @Override
    public List<GenericDbProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericDbProductDto createProduct(GenericDbProductDto product) {
        return null;
    }

    @Override
    public GenericDbProductDto deleteProductById(UUID id) {
        return null;
    }

    @Override
    public GenericDbProductDto updateProductById(UUID id, GenericDbProductDto updatedProduct) {
        return null;
    }
}
