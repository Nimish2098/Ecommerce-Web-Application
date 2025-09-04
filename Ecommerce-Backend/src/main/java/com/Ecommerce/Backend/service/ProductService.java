package com.Ecommerce.Backend.service;

import com.Ecommerce.Backend.entity.Product;
import com.Ecommerce.Backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }

    public Product updateProduct(Long id, Product product){
            Product existingProduct = getProductById(id);
            existingProduct.setName(product.getName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setImageUrl(product.getImageUrl());
            existingProduct.setStock(product.getStock());
            return productRepository.save(product);

    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
