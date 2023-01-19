package com.example.SpringBootProject.Controller;


import com.example.SpringBootProject.DTO.ProductDTO;
import com.example.SpringBootProject.Entity.Product;
import com.example.SpringBootProject.Repository.ProductRepo;
import com.example.SpringBootProject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String AddProducts(){

        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String AddProducts(@ModelAttribute ("dto") ProductDTO dto ,Model model){

     Product p=new Product();
     p.setId(p.getId());
     p.setName(dto.getName());
     p.setPrice(dto.getPrice());
     p.setDescription(dto.getDescription());

     productService.AddProduct(p);

        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id ,Model model){

        Product p=productService.findProductById(id).get();
        ProductDTO dto=new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setDescription(p.getDescription());

        model.addAttribute("products" ,productService.findAllProducts());
        model.addAttribute("dto",dto);

        return "addProduct";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id ){

        productService.deleteProductById(id);
        return "redirect:/products";
    }

}
