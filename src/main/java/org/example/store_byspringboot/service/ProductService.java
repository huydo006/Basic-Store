package org.example.store_byspringboot.service;

import org.example.store_byspringboot.dto.ProductDTO;
import org.example.store_byspringboot.model.Product;
import org.example.store_byspringboot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        // Logic to get all products
        return productRepository.findAll();
    }

    public Product getProductById(Integer id){

        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Integer id , Product newProduct){
        Product product = productRepository.findById(id).orElse(null);
        product.setName(newProduct.getName()) ;
        product.setPrice(newProduct.getPrice());
        product.setDescription(newProduct.getDescription());
        product.setStock(newProduct.getStock());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }
}
