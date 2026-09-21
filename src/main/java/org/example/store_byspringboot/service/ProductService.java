package org.example.store_byspringboot.service;

import org.example.store_byspringboot.ModelMapperConfig;
import org.example.store_byspringboot.dto.ProductResponse;
import org.example.store_byspringboot.model.Product;
import org.example.store_byspringboot.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//        List<ProductResponse> productResponses = new ArrayList<>();
//
//        for (Product product : products) {
//            ProductResponse response = new ProductResponse();
//            response.setProductId(product.getId());
//            response.setProductName(product.getName());
//            response.setDescription(product.getDescription());
//            response.setPrice(product.getPrice());
//            response.setStock(product.getStock());
//            response.setCreatedAt(product.getCreatedAt());
//            response.setUpdatedAt(product.getUpdatedAt());
//
//            productResponses.add(response);
//        }
//
//        return productResponses;

        //----------*********-----------

        List<ProductResponse> responses = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for(Product p : productList){
            ProductResponse response = modelMapper.map(p,ProductResponse.class);
            responses.add(response);
        }

        return responses;

        //************




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
