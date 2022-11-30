package org.example;

import org.example.clients.CategoryClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ：林梓龙
 * <p>后台管理启动类</p>
 * @description：TODO
 * @date ：2022/11/20 0020 11:22
 */
@MapperScan("org.example.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {CategoryClient.class})
@EnableCaching
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
