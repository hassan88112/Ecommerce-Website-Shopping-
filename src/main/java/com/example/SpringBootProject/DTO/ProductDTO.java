package com.example.SpringBootProject.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private  Long id;
    private String fullName;
    private Long age;
    private LocalDate date;
    private String startDate;
    private String endDate;
    private String gender;
    private String name;
    private Long price;
    private String description;


}
