package com.sit.int202.backend.Product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductList() {
        return new ResponseEntity<List<Product>>(productService.getProductList(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return new ResponseEntity<Product>(productService.getProductById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProductListByCategoryId(@RequestParam(value = "category") long id) {
        List<Product> productList = productService.getProductListByCategoryId(id);
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable long id) {
        return new ResponseEntity<Long>(productService.delete(id), HttpStatus.OK);
    }
}
