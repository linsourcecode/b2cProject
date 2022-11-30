package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.example.parms.ProductSearchParams;
import org.example.pojo.Product;
import org.example.service.ProductService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 产品搜索服务
 * */
@RestController
@RequestMapping("product")
@Slf4j
public class ProductSearchController {
    @Autowired
    private ProductService productService;


    @GetMapping("/list")
    public List<Product> allList(){

         log.info("所有商品的数据查询成功");
         return  productService.allList();

    }
    @PostMapping("/search")
    public R search(@RequestBody ProductSearchParams productSearchParams) throws JsonProcessingException {
        return productService.search(productSearchParams);
    }

}
