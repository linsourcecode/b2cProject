package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.parms.CartListParam;
import org.example.parms.CartSaveParam;
import org.example.pojo.Cart;
import org.example.service.CartService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：林梓龙
 * <p>购物车方法请求</p>
 * @description：TODO
 * @date ：2022/11/19 0019 14:59
 */
@RestController
@RequestMapping("cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;
    @PostMapping("save")
    public R save(@RequestBody @Validated CartSaveParam cartSaveParam, BindingResult bindingResult){
        log.info(cartSaveParam.getUserId()+"-"+"添加商品"+cartSaveParam.getProductId());
        if(bindingResult.hasErrors()){
            return  R.fail("参数缺失，添加失败");
        }
        return  cartService.save(cartSaveParam);

    }
    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam cartListParam, BindingResult bindingResult){

        if (bindingResult.hasErrors()){

            return R.fail("购物车数据查询失败!");
        }

        return cartService.list(cartListParam.getUserId());
    }
    @PostMapping("update")
    public R update(@RequestBody Cart cart){

        return cartService.update(cart);
    }
    @PostMapping("remove")
    public R remove(@RequestBody Cart cart){

        return cartService.remove(cart);
    }


}
