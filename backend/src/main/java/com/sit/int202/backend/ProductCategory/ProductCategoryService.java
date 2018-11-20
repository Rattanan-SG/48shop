package com.sit.int202.backend.ProductCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getProductCategoryList() {
        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
        return productCategoryList;
    }

    public ProductCategory getProductCategoryById(long id) {
        ProductCategory productCategory = productCategoryRepository.getOne(id);
        return productCategory;
    }

    public ProductCategory getProductByCategoryByName(String name) {
        ProductCategory productList = productCategoryRepository.findProductCategoryByName(name);
        return productList;
    }

    public ProductCategory create(ProductCategory productCategory) {
        ProductCategory productCategory_return = productCategoryRepository.save(productCategory);
        return productCategory_return;
    }

    public long delete(long productCategory_id) {
        productCategoryRepository.deleteById(productCategory_id);
        return productCategory_id;
    }

}
