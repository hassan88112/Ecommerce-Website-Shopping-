package com.example.SpringBootProject.Controller;


import com.example.SpringBootProject.DTO.ProductDTO;
import com.example.SpringBootProject.Entity.Product;
import com.example.SpringBootProject.Repository.ProductRepo;
import com.example.SpringBootProject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ProductController {


    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepo productRepo;


    @GetMapping("/products")
    public String AllProducts(Model model){

        model.addAttribute("products" ,productService.findAllProducts());
        return "products";
    }
    @GetMapping("/addProduct")
    public String AddProducts(Model model){

        model.addAttribute("dto",new ProductDTO());

        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String AddProducts(@ModelAttribute ("dto") ProductDTO dto , Model model )  {


        Product p=new Product();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setDescription(dto.getDescription());

        productService.AddProduct(p);

        return "redirect:/products";
    }

    @GetMapping("/update/{id}")         // error f id nseet adeef in html 2lshan y detech 2leh
    public String update(@PathVariable Long id ,Model model){

            Product p=productService.findProductById(id).get();
            ProductDTO dto=new ProductDTO();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setPrice(p.getPrice());
            dto.setDescription(p.getDescription());
            model.addAttribute("dto",dto);

            return "addProduct";


    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id ){

        productService.deleteProductById(id);
        return "redirect:/products";
    }
    @GetMapping("/example")
    public String example(){

        return "example";
    }

    @GetMapping("/AboutUs")
    public String aboutus(){

        return "AboutUs";
    }






}
