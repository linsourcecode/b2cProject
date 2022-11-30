package org.example.controller;

import org.example.parms.ProductCollectParam;
import org.example.parms.ProductIdParam;
import org.example.pojo.Product;
import org.example.service.ProductService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：林梓龙
 * <p>购物车服务方法</p>
 * @description：TODO
 * @date ：2022/11/19 0019 14:42
 */
@RequestMapping("product")
@RestController
public class ProductCartController {
    @Autowired
    private ProductService productService;
    @PostMapping("cart/detail")
    public Product cdetail(@RequestBody @Validated ProductIdParam productIdParam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  null;
        }
        R detail = productService.detail(productIdParam.getProductID());
        Product product= ((Product) detail.getData());
        return  product;
    }

    @PostMapping("cart/list")
    public List<Product> cartList(@RequestBody @Validated ProductCollectParam productCollectParam,
                                  BindingResult result){

        if (result.hasErrors()){
            return new ArrayList<Product>();
        }

        return productService.cartList(productCollectParam.getProductIds());
    }
}
