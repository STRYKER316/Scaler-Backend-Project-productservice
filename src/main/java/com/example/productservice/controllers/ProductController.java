package com.example.productservice.controllers;

import java.util.List;
import java.util.UUID;

import com.example.productservice.dtos.DbProductDto;
import com.example.productservice.services.SelfDbProductService;
import org.springframework.web.bind.annotation.*;

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
//    public GenericProductDto updateProductById(@PathVariable("id") long id,
//                                               @RequestBody GenericProductDto updatedProduct) {
//        return productService.updateProductById(id, updatedProduct);
//    }



    //    ------------------------------ DB Repo Implementation ------------------------------
    private SelfDbProductService selfDbProductService;

    // Constructor
    public ProductController(SelfDbProductService selfDbProductService) {
        this.selfDbProductService = selfDbProductService;
    }


    @GetMapping("/{id}")
    public DbProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
//        System.out.println("The id is: " + id);
        return selfDbProductService.getProductById(id);
    }


    @GetMapping
    public List<DbProductDto> getAllProducts() {
        return selfDbProductService.getAllProducts();
    }


    @PostMapping
    public Boolean createProduct(@RequestBody DbProductDto product) {
         System.out.println(product.getTitle());
        return selfDbProductService.createProduct(product);
    }


     @DeleteMapping("/{id}")
     public void deleteProductById(@PathVariable("id") UUID id) {
         selfDbProductService.deleteProductById(id);
     }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") long id) {
//        ResponseEntity<GenericProductDto> response = new ResponseEntity<GenericProductDto>(
//                selfDbProductService.deleteProductById(id), HttpStatus.OK
//        );
//        return response;
//
//        // return productService.deleteProductById(id);
//    }


    @PutMapping("/{id}")
    public DbProductDto updateProductById(@PathVariable("id") UUID id,
                                          @RequestBody DbProductDto updatedProduct) {
        return selfDbProductService.updateProductById(id, updatedProduct);
    }
}
