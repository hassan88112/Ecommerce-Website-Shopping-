package com.example.SpringBootProject.Repository;

import com.example.SpringBootProject.Entity.ExcelTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelRepo extends JpaRepository<ExcelTest,Long> {
}
