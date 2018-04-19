package com.wiredbraincoffe.productapiannotation.controller;

import com.wiredbraincoffe.productapiannotation.model.Product;
import com.wiredbraincoffe.productapiannotation.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public Flux<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
