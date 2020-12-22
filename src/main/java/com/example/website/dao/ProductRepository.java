package com.example.website.dao;

import com.example.website.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,Long> {

    Product findByName(String name);

    List<Product> findAllByPriceAfter(double price);

    List<Product> findByNameContaining(String title);

    List<Product> findAllByCategoryEquals(String category);

    //@Query(value = "{'category': {$e:?0}}")
    //List<Product> findAllByCategory(String category);
}
