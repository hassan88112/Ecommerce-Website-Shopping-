package com.example.SpringBootProject.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDTO {

    private String id;
    private String firstName;
    private Long salary;
}
