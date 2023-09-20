package com.example.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.productservice.dtos.GenericFakeStoreProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.thirdPartyClients.productService.fakeStore.FakeStoreProductServiceClient;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    // Constructor
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }


    @Override
    public GenericFakeStoreProductDto getProductById(long id) throws NotFoundException {
        return fakeStoreProductServiceClient.getProductById(id);
    }


    @Override
    public List<GenericFakeStoreProductDto> getAllProducts() {
        return fakeStoreProductServiceClient.getAllProducts();
    }


    @Override
    public GenericFakeStoreProductDto createProduct(GenericFakeStoreProductDto product) {
        return fakeStoreProductServiceClient.createProduct(product);
    }


    @Override
    public GenericFakeStoreProductDto deleteProductById(long id) {
        return fakeStoreProductServiceClient.deleteProductById(id);
    }


    @Override
    public GenericFakeStoreProductDto updateProductById(long id, GenericFakeStoreProductDto updatedProduct) {
        return fakeStoreProductServiceClient.updateProductById(id, updatedProduct);
    }
    
}
