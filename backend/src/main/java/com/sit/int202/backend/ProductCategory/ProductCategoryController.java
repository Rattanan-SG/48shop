package com.sit.int202.backend.ProductCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getProductCategoryList() {
        List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList();
        return new ResponseEntity<>(productCategoryList, HttpStatus.OK);
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<ProductCategory> getProductCategory(@PathVariable("product_id") long category_id){
        ProductCategory productCategory = productCategoryService.getProductCategory(category_id);
        return new ResponseEntity<>(productCategory, HttpStatus.OK);
    }
}