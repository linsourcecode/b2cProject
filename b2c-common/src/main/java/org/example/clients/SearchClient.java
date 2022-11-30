package org.example.clients;


import org.example.parms.ProductSearchParam;
import org.example.parms.ProductSearchParams;
import org.example.pojo.Product;
import org.example.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * projectName: b2c-store
 * <p>
 * description: 搜索服务调用客户端
 */
@FeignClient("search-service")
public interface SearchClient {

    @PostMapping("/search/product")
    R search(@RequestBody ProductSearchParam productSearchParam);

    @PostMapping("/search/save")
    R saveOrUpdate(@RequestBody Product product);

    @PostMapping("/search/remove")
    R remove(@RequestBody Integer productId);
    @PostMapping("/search/")
    R searchProduct(ProductSearchParams productSearchParams);
}
