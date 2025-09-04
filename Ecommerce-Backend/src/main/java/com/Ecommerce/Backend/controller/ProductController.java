package com.Ecommerce.Backend.controller;

import com.Ecommerce.Backend.entity.Product;
import com.Ecommerce.Backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final  ProductService productService;

    @PostMapping
    public String addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return "Created Successfully";
    }

    @DeleteMapping("/{id}")
    public String deteteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "Deleted Successfully";
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable Long id){
        return productService.updateProduct(id,product);
    }


}
