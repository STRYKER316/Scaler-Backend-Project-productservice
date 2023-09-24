package com.example.productservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.productservice.dtos.DbProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Price;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Primary
@Service("selfDbProductService")
public class SelfDbProductService implements DbProductService{

    private ProductRepository productRepository;

    public SelfDbProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Helper Method
    public DbProductDto convertProductToDbProductDto(Product product) {
        DbProductDto genericProductDto = new DbProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice().getPrice());

        return genericProductDto;
    }


//    ------------------------------ APIs ------------------------------
    @Override
    public DbProductDto getProductById(UUID id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return convertProductToDbProductDto(product.get());
        } else {
            throw new NotFoundException("The product with id: " + id + " is not found");
        }
    }


    @Override
    public List<DbProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<DbProductDto> dbProductDtos = new ArrayList<>();

        for (Product product : products) {
            DbProductDto dbProductDto = convertProductToDbProductDto(product);
            dbProductDtos.add(dbProductDto);
        }
        return dbProductDtos;
    }


    @Override
    public Boolean createProduct(DbProductDto product) {
        Product newProduct = new Product();
        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setImage(product.getImage());
        newProduct.setCategory(new Category(product.getCategory()));
        newProduct.setPrice(new Price("Rupee", product.getPrice()));

        if (productRepository.save(newProduct) == null) {
            return false;
        }
        return true;
    }


    @Override
    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);
    }


    @Override
    public DbProductDto updateProductById(UUID id, DbProductDto updatedProduct) {
        Optional<Product> dbProduct = productRepository.findById(id);
        Product productToUpdate = dbProduct.get();

        productToUpdate.setTitle(updatedProduct.getTitle());
        productToUpdate.setDescription(updatedProduct.getDescription());
        productToUpdate.setImage(updatedProduct.getImage());
        productRepository.save(productToUpdate);

        return convertProductToDbProductDto(productToUpdate);
    }
}
