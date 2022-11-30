package org.example;

import org.example.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ：林梓龙
 * <p>购物车服务</p>
 * @description：TODO
 * @date ：2022/11/19 0019 14:22
 */
@SpringBootApplication
@MapperScan("org.example.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class ChartApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChartApplication.class,args);
    }
}
