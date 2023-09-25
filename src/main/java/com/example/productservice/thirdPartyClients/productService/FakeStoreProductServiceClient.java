package com.example.productservice.thirdPartyClients.productService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.GenericFakeStoreProductDto;
import com.example.productservice.exceptions.NotFoundException;

/*
* Client wrapper over FakeStore API
* */

@Service
public class FakeStoreProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;

    // private String productsRequestBaseUrl = "https://fakestoreapi.com/products";
    // private String productRequestUrlWithId = "https://fakestoreapi.com/products/{id}";

    private String productsRequestBaseUrl;
    private String productRequestUrlWithId;

    // Constructor
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, 
                                            @Value("${fakestore.api.url}") String fakeStoreApiUrl, 
                                            @Value("${fakestore.api.path.products}") String fakeStoreProductApiPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productsRequestBaseUrl = fakeStoreApiUrl + fakeStoreProductApiPath;
        this.productRequestUrlWithId = fakeStoreApiUrl + fakeStoreProductApiPath + "/{id}";;
    }


    public FakeStoreProductDto getProductById(long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(productRequestUrlWithId, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        // exception handling
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }

        return fakeStoreProductDto;
        // return "Here is the product id: " + id;
        // return "Hello";
    }


    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productsRequestBaseUrl, FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();

        // System.out.println(fakeStoreProductDtos);
        return Arrays.stream(fakeStoreProductDtos).toList();
    }


    public FakeStoreProductDto createProduct(GenericFakeStoreProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
            productsRequestBaseUrl, product, FakeStoreProductDto.class
        );
        return response.getBody();
    }


    public FakeStoreProductDto deleteProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(productRequestUrlWithId, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }


    // public void deleteProductById(long id) {
    //     // System.out.println("Product with id: " + id + " is deleted");

    //     RestTemplate restTemplate = restTemplateBuilder.build();
    //     restTemplate.delete(productRequestUrlWithId, id);

    // }



    public FakeStoreProductDto updateProductById(long id, GenericFakeStoreProductDto updatedProduct) {
        HttpEntity<GenericFakeStoreProductDto> updatedProductEntity = new HttpEntity<GenericFakeStoreProductDto>(updatedProduct);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(productRequestUrlWithId, HttpMethod.PUT, updatedProductEntity, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }
    
}
