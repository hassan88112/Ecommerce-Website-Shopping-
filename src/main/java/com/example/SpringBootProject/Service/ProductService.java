package com.example.SpringBootProject.Service;


import com.example.SpringBootProject.Entity.Product;
import com.example.SpringBootProject.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepo productRepo;

    public List<Product> findAllProducts(){

        return productRepo.findAll();
    }
    public Optional<Product> findProductById(Long id){

        return productRepo.findById(id);
    }

    public Product AddProduct(Product p){

        return productRepo.save(p);
    }
    public void deleteProductById(Long id){

        productRepo.deleteById(id);
    }
}
