package com.example.productservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.productservice.dtos.GenericDbProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Primary
@Service("selfProductService")
public class SelfProductService implements DbProductService{

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Helper Method
    private GenericDbProductDto convertProductToGenericDbProductDto(Product product) {
        GenericDbProductDto genericProductDto = new GenericDbProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice().getPrice());

        return genericProductDto;
    }


//    ------------------------------ APIs ------------------------------
    @Override
    public GenericDbProductDto getProductById(UUID id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return convertProductToGenericDbProductDto(product.get());
        } else {
            throw new NotFoundException("The product with id: " + id + " is not found");
        }
    }


    @Override
    public List<GenericDbProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericDbProductDto> genericDbProductDtos = new ArrayList<>();

        for (Product product : products) {
            GenericDbProductDto genericDbProductDto = convertProductToGenericDbProductDto(product);
            genericDbProductDtos.add(genericDbProductDto);
        }
        return genericDbProductDtos;
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
