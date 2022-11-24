package org.example.listener;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.example.clients.ProductClient;
import org.example.pojo.Product;
import org.example.pojo.ProductDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：林梓龙
 * <p>监控es和mysql的数据同步</p>
 * @description：TODO
 * @date ：2022/11/17 0017 17:48
 */
@Component
@Slf4j
public class SpringbootListener implements ApplicationRunner {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ProductClient productClient;
    private String createIndex = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"productId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productName\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"categoryId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productTitle\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productIntro\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productPicture\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"productPrice\":{\n" +
            "        \"type\": \"double\",\n" +
            "        \"index\": true\n" +
            "      },\n" +
            "      \"productSellingPrice\":{\n" +
            "        \"type\": \"double\"\n" +
            "      },\n" +
            "      \"productNum\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productSales\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
    /**
     * 需要此方法，实现数据同步
     *
     * */
    @Override
    public void run(ApplicationArguments arguments) throws  Exception{
        //数据库数据初始化
        //查询全部数据
        List<Product> list = productClient.allList();

        //判断是否存在product索引
        GetIndexRequest getIndexRequest = new GetIndexRequest("product");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);


        if (!exists){
            //不存在,创建新索引
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("product");
            createIndexRequest.source(createIndex, XContentType.JSON);
            client.indices().create(createIndexRequest,RequestOptions.DEFAULT);
        }

        //删除全部数据
        DeleteByQueryRequest deleteIndexRequest = new DeleteByQueryRequest("product");
        deleteIndexRequest.setQuery(QueryBuilders.matchAllQuery());
        client.deleteByQuery(deleteIndexRequest, RequestOptions.DEFAULT);



        //插入全部数据
        BulkRequest bulkRequest = new BulkRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Product product : list) {
            ProductDoc productDoc = new ProductDoc(product);
            log.info("插入数据",product.getCategoryId());

            bulkRequest.add(new IndexRequest("product")
                    .id(productDoc.getProductId().toString())
                    .source(objectMapper.writeValueAsString(productDoc), XContentType.JSON));
        }

        client.bulk(bulkRequest,RequestOptions.DEFAULT);

        log.info("ApplicationRunListener.run业务结束，完成数据更新!");
    }
}
