package com.sit.int202.backend.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProductService {
    @Autowired
    private ProductRepository productRepository;
}