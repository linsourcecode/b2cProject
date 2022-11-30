package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* 轮播图的启动类
* */
@MapperScan(basePackages = "org.example.mapper")
@SpringBootApplication
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }
}
