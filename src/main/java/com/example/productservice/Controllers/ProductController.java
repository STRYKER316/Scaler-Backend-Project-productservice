package com.example.productservice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.dtos.ExceptionDto;
import com.example.productservice.dtos.GenericProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.services.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    // Constructor
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> notFoundExceptionHandler(NotFoundException exception) {
        // System.out.println("Not found exception occured.");
        
        return new ResponseEntity<ExceptionDto>(
            new ExceptionDto(HttpStatus.NOT_FOUND, exception.getMessage()),
            HttpStatus.NOT_FOUND
        );
    }


    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") long id) throws NotFoundException {
        // return "Here is the product id: " + id;
        return productService.getProductById(id);
    }


    // @DeleteMapping("/{id}")
    // public void deleteProductById(@PathVariable("id") long id) {
    //     productService.deleteProductById(id);
    // }


    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") long id) {
        ResponseEntity<GenericProductDto> response = new ResponseEntity<GenericProductDto>(
            productService.deleteProductById(id), HttpStatus.OK
        );
        return response;

        // return productService.deleteProductById(id);
    }


    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        // System.out.println(product.getTitle());
        return productService.createProduct(product);
    }

    
    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@PathVariable("id") long id) {
        return productService.updateProductById(id);
    }

}
