package com.example.productservice.services;

import java.util.ArrayList;
import java.util.List;

import com.example.productservice.dtos.FakeStoreProductDto;
import org.springframework.stereotype.Service;
import com.example.productservice.dtos.GenericFakeStoreProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.thirdPartyClients.productService.FakeStoreProductServiceClient;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    // Constructor
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }


    private GenericFakeStoreProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericFakeStoreProductDto product = new GenericFakeStoreProductDto();

        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }


    @Override
    public GenericFakeStoreProductDto getProductById(long id) throws NotFoundException {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }


    @Override
    public List<GenericFakeStoreProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreProductServiceClient.getAllProducts();

        List<GenericFakeStoreProductDto> genericFakeStoreProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            genericFakeStoreProductDtos.add(convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto));
        }
        return genericFakeStoreProductDtos;
    }


    @Override
    public GenericFakeStoreProductDto createProduct(GenericFakeStoreProductDto product) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }


    @Override
    public GenericFakeStoreProductDto deleteProductById(long id) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }


    @Override
    public GenericFakeStoreProductDto updateProductById(long id, GenericFakeStoreProductDto updatedProduct) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductServiceClient.updateProductById(id, updatedProduct));
    }
    
}
