package com.sit.int202.backend.Product;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductListByCategoryId(long id) {
        return productRepository.findByProductCategoryId(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public long delete(long id) {
        productRepository.deleteById(id);
        return id;
    }
}
