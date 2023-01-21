package com.example.SpringBootProject.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private  Long id;
    private String fullName;
    private String gender;
    private String name;
    private Long price;
    private String description;
}
