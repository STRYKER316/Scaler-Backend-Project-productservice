package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    <S extends Product> S save(S entity);

    Product findByTitleEquals(String title);

    Product findByTitleAndPrice_Price(String title, double price);

    List<Product> findAllByPrice_Currency(String currency);


    long countAllByPrice_Currency(String currency);

    @Query(value = "select * from Product where title = :title", nativeQuery = true)
    List<Product> findAllByTitle(String title);


    // ------------------------------ SelfDbProductService Methods below ------------------------------
    @Override
    Optional<Product> findById(UUID uuid);

    @Override
    List<Product> findAll();

    @Override
    void deleteById(UUID uuid);


}
