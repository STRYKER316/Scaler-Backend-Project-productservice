package com.example.productservice.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products/")
public class ProductController {
    @GetMapping
    public void getAllProducts() {

    }


    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") long id) {
        return "Here is the product id: " + id;
    }


    @DeleteMapping("{id}")
    public void deleteProductById() {

    }


    @PostMapping
    public String createProduct() {
        return "Created new product " + 1;
    }

    @PutMapping("{id}")
    public void updateProductById() {

    }

}
