package com.example.SpringBootProject.Controller;


import com.example.SpringBootProject.DTO.DateRange;
import com.example.SpringBootProject.DTO.ProductDTO;
import com.example.SpringBootProject.Entity.Product;
import com.example.SpringBootProject.Repository.ProductRepo;
import com.example.SpringBootProject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        model.addAttribute("DateRange",new DateRange());
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
        p.setFullName(dto.getFullName());
        p.setAge(dto.getAge());
        p.setDate(dto.getDate());
        p.setGender(dto.getGender());
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
            dto.setFullName(p.getFullName());
            dto.setAge(p.getAge());
            dto.setDate(p.getDate());
            dto.setGender(p.getGender());
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
    @PostMapping("/GetCustomer")
    public String getCustomerByDate(Model model, @ModelAttribute DateRange dateRange){

        List<Product> p=productService.getCustomerByDate(dateRange.getStartDate(), dateRange.getEndDate());
        model.addAttribute("products",p);
        model.addAttribute("DateRange",new DateRange());

        return  "products";
    }

    @GetMapping("/test")
    public String test(){

        return "test";
    }

    @GetMapping("/AboutUs")
    public String aboutus(){

        return "AboutUs";
    }






}
