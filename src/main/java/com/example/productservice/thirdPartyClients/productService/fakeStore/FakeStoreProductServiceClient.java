package com.example.productservice.thirdPartyClients.productService.fakeStore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;


@Service
public class FakeStoreProductServiceClient {

    private RestTemplateBuilder restTemplateBuilder;

    private String productsRequestBaseUrl = "https://fakestoreapi.com/products";
    private String productRequestUrlWithId = "https://fakestoreapi.com/products/{id}";

    // // application properties configuration
    // @Value("${fakestore.api.url}")
    // private String fakeStoreApiUrl;

    // @Value("${fakestore.api.path.products}")
    // private String fakeStoreProductApiPath;

    // private String productsRequestBaseUrl = fakeStoreApiUrl + fakeStoreProductApiPath;
    // private String productRequestUrlWithId = fakeStoreApiUrl + fakeStoreProductApiPath + "/{id}";

    // Constructor
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    private GenericProductDto convertFakeStorDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();

        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }


    public GenericProductDto getProductById(long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(productRequestUrlWithId, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        // exception handling
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }

        return convertFakeStorDtoToGenericProductDto(fakeStoreProductDto);
        // return "Here is the product id: " + id;
        // return "Hello";
    }


    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productsRequestBaseUrl, FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProductDtos = response.getBody();
        List<GenericProductDto> products = new ArrayList<GenericProductDto>();

        if (fakeStoreProductDtos != null) {
            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            GenericProductDto product = convertFakeStorDtoToGenericProductDto(fakeStoreProductDto);
            products.add(product);
            }
        }
        
        // System.out.println(fakeStoreProductDtos);
        return products;
    }


    public GenericProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
            productsRequestBaseUrl, product, GenericProductDto.class
        );
        return response.getBody();
    }


    public GenericProductDto deleteProductById(long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(productRequestUrlWithId, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return convertFakeStorDtoToGenericProductDto(fakeStoreProductDto);
    }


    // public void deleteProductById(long id) {
    //     // System.out.println("Product with id: " + id + " is deleted");

    //     RestTemplate restTemplate = restTemplateBuilder.build();
    //     restTemplate.delete(productRequestUrlWithId, id);

    // }



    public GenericProductDto updateProductById(long id, GenericProductDto updatedProduct) {
        HttpEntity<GenericProductDto> updatedProductEntity = new HttpEntity<GenericProductDto>(updatedProduct);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(productRequestUrlWithId, HttpMethod.PUT, updatedProductEntity, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStorDtoToGenericProductDto(fakeStoreProductDto);
    }
    
}
