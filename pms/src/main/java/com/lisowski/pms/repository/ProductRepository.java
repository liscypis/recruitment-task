package com.lisowski.pms.repository;

import com.lisowski.pms.entity.Category;
import com.lisowski.pms.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByProductName(String pName);
    List<Product> findByCategory(Category category);
}
