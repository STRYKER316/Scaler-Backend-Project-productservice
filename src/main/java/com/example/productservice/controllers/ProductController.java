package com.example.productservice.controllers;

import java.util.List;
import java.util.UUID;

import com.example.productservice.dtos.GenericDbProductDto;
import com.example.productservice.services.SelfProductService;
import org.springframework.web.bind.annotation.*;

import com.example.productservice.dtos.GenericFakeStoreProductDto;
import com.example.productservice.exceptions.NotFoundException;


@RestController
@RequestMapping("/products")
public class ProductController {

////    ------------------------------ FakeStore Implementation ------------------------------
//    private ProductService productService;
//
//    // Constructor
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//
//    @GetMapping
//    public List<GenericProductDto> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
//
//    @GetMapping("/{id}")
//    public GenericProductDto getProductById(@PathVariable("id") long id) throws NotFoundException {
//        // return "Here is the product id: " + id;
//        return productService.getProductById(id);
//    }
//
//
//    // @DeleteMapping("/{id}")
//    // public void deleteProductById(@PathVariable("id") long id) {
//    //     productService.deleteProductById(id);
//    // }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") long id) {
//        ResponseEntity<GenericProductDto> response = new ResponseEntity<GenericProductDto>(
//            productService.deleteProductById(id), HttpStatus.OK
//        );
//        return response;
//
//        // return productService.deleteProductById(id);
//    }
//
//
//    @PostMapping
//    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        // System.out.println(product.getTitle());
//        return productService.createProduct(product);
//    }
//
//
//    @PutMapping("/{id}")
//    public GenericProductDto updateProductById(@PathVariable("id") long id, @RequestBody GenericProductDto updatedProduct) {
//        return productService.updateProductById(id, updatedProduct);
//    }



    //    ------------------------------ DB Repo Implementation ------------------------------
    private SelfProductService selfProductService;

    // Constructor
    public ProductController(SelfProductService selfProductService) {
        this.selfProductService = selfProductService;
    }


    @GetMapping("/{id}")
    public GenericDbProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
//        System.out.println("The id is: " + id);
        return selfProductService.getProductById(id);
    }


    @GetMapping
    public List<GenericDbProductDto> getAllProducts() {
        return selfProductService.getAllProducts();
    }


    @PostMapping
    public Boolean createProduct(@RequestBody GenericDbProductDto product) {
         System.out.println(product.getTitle());
        return selfProductService.createProduct(product);
    }


    // @DeleteMapping("/{id}")
    // public void deleteProductById(@PathVariable("id") long id) {
    //     productService.deleteProductById(id);
    // }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") long id) {
//        ResponseEntity<GenericProductDto> response = new ResponseEntity<GenericProductDto>(
//                selfProductService.deleteProductById(id), HttpStatus.OK
//        );
//        return response;
//
//        // return productService.deleteProductById(id);
//    }
//
//
//    @PutMapping("/{id}")
//    public GenericProductDto updateProductById(@PathVariable("id") long id, @RequestBody GenericProductDto updatedProduct) {
//        return selfProductService.updateProductById(id, updatedProduct);
//    }

}
