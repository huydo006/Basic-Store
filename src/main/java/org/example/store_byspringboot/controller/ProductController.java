package org.example.store_byspringboot.controller;

import org.example.store_byspringboot.model.Product;
import org.example.store_byspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    final ProductService productService;
    private ProductController(@RequestBody ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // Logic to create a product
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        // Logic to get all products
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        // Logic to get a product by ID
        return productService.getProductById(id);
    }

    @DeleteMapping({"/{id}"})
    public void deleteProduct(@PathVariable Integer id) {
        // Logic to delete a product by ID
        productService.deleteProduct(id);
    }

    @PutMapping({"/{id}"})
    public Product updateProduct(@PathVariable Integer id , @RequestBody Product newproduct){
        return productService.updateProduct(id, newproduct);
    }



}
