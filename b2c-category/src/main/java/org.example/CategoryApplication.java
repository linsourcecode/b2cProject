package org.example;

import org.example.pojo.Category;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.example.mapper")
public class CategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryApplication.class,args);
    }
}
