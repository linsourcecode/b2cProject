package org.example.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.parms.ProductSearchParams;
import org.example.service.SearchService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：林梓龙
 * <p>搜索服务控制类</p>
 * @description：TODO
 * @date ：2022/11/18 0018 10:17
 */
@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService service;
    @PostMapping("product")
    public R searchProduct(@RequestBody ProductSearchParams productSearchParams) throws JsonProcessingException {
         return  service.search(productSearchParams);
    }
}