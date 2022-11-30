package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.parms.ProductCollectParam;
import org.example.service.ProductService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：林梓龙
 * <p>商品收藏控制方法</p>
 * @description：TODO
 * @date ：2022/11/18 0018 21:03
 */
@RestController
@RequestMapping("product")
@Slf4j
public class ProductCollectController {
    @Autowired
    private ProductService productService;
    @PostMapping("/collect/list")
    public R productIds(@RequestBody ProductCollectParam productCollectParam){

        log.info("返回"+productCollectParam.getProductIds());
        return productService.ids(productCollectParam.getProductIds());

    }
}
