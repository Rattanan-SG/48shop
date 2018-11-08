package com.sit.int202.backend.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ProductController {
    @Autowired
    private ProductService productService;
   
}