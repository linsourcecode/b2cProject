package org.example;

import org.example.clients.ProductClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ：林梓龙
 * <p>收藏服务</p>
 * @description：TODO
 * @date ：2022/11/18 0018 16:01
 */
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class CollectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class,args);
    }
}
