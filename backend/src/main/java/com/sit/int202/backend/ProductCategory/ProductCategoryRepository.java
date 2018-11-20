package com.sit.int202.backend.ProductCategory;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

	public ProductCategory findProductCategoryByName(String name);

}