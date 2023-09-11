package com.example.productservice.services;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.thirdPartyClients.productService.fakeStore.FakeStoreProductServiceClient;


@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    // Constructor
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }


    @Override
    public GenericProductDto getProductById(long id) throws NotFoundException {
        return fakeStoreProductServiceClient.getProductById(id);
    }


    @Override
    public List<GenericProductDto> getAllProducts() {
        return fakeStoreProductServiceClient.getAllProducts();
    }


    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return fakeStoreProductServiceClient.createProduct(product);
    }


    @Override
    public GenericProductDto deleteProductById(long id) {
        return fakeStoreProductServiceClient.deleteProductById(id);
    }


    @Override
    public GenericProductDto updateProductById(long id, GenericProductDto updatedProduct) {
        return fakeStoreProductServiceClient.updateProductById(id, updatedProduct);
    }
    
}
