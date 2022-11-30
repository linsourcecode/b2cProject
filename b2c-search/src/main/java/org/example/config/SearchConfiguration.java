package org.example.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：林梓龙
 * <p>消息队列的配置</p>
 * @description：TODO
 * @date ：2022/11/17 0017 17:41
 */
@Configuration
public class SearchConfiguration {
    /**
     * mq序列化方式，选择json！
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }

    /**
     * es客户端添加到ioc容器
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() throws InterruptedException {
        HttpHost host=new HttpHost("data101", 9200, HttpHost.DEFAULT_SCHEME_NAME);
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "lin123abc"));;
        RestClientBuilder clientBuilder= RestClient.builder(host);
        clientBuilder.setHttpClientConfigCallback(f->f.setDefaultCredentialsProvider(credentialsProvider));
        RestHighLevelClient client =
                new RestHighLevelClient(clientBuilder);

        return client;
    }
}
