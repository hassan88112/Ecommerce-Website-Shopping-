package com.example.SpringBootProject.Repository;

import com.example.SpringBootProject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    @Query(
            value = "SELECT * FROM product WHERE product.date BETWEEN :start_date AND :end_date ",
            nativeQuery = true)
    public List<Product> getCustomerByDate(@Param("start_date") String startDate, @Param("end_date") String endDate);

}
