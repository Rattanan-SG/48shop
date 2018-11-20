package com.sit.int202.backend.ProductCategory;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getProductCategoryList() {
        List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList();
        return new ResponseEntity<List<ProductCategory>>(productCategoryList, HttpStatus.OK);
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable(value = "category_id") long id) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        return new ResponseEntity<ProductCategory>(productCategory, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<ProductCategory> getProductCategoryByName(@RequestParam(value = "name") String name) {
        ProductCategory productCategory = productCategoryService.getProductByCategoryByName(name);
        return new ResponseEntity<ProductCategory>(productCategory, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<ProductCategory> createProductCategory(@Valid @RequestBody ProductCategory productCategory) {
        ProductCategory productCategory_return = productCategoryService.create(productCategory);
        return new ResponseEntity<ProductCategory>(productCategory_return, HttpStatus.CREATED);
    }

    @PutMapping("/category/{category_id}")
    public ResponseEntity<ProductCategory> updateProductCategoryName(
            @Valid @PathVariable(value = "category_id") long id, @RequestBody ProductCategory productCategory) {
        ProductCategory productCategoryThisId = productCategoryService.getProductCategoryById(id);
        productCategoryThisId.setName(productCategory.getName());
        ProductCategory productCategory_return = productCategoryService.create(productCategoryThisId);
        return new ResponseEntity<ProductCategory>(productCategory_return, HttpStatus.OK);
    }
}