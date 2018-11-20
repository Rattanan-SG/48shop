package com.sit.int202.backend.Product;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByProductCategoryId(long id);

}
