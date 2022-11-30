package org.example.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

/**
 * @author ：林梓龙
 * <p>商品缓存配置类</p>
 * @description：TODO
 * @date ：2022/11/18 0018 13:05
 */
//@Configuration
public class ProductCacheConfiguration extends CacheConfiguration {
    /**
     * mq序列化方式，选择json！
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }
}
