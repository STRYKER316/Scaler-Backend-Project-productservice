package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

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
}
