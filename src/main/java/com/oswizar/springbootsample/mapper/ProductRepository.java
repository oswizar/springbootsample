package com.oswizar.springbootsample.mapper;

import com.oswizar.springbootsample.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(Double price1, Double price2);

    List<Product> findByNameContaining(String keyword);
}
