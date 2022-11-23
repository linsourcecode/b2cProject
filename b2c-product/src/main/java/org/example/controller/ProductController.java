package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.parms.ProductHotParam;
import org.example.parms.ProductIdParam;
import org.example.parms.ProductIdsParam;
import org.example.service.ProductService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(path = "promo",method=RequestMethod.GET)
    public R promo( @RequestParam(value = "category")  String category){

        return productService.promo(category);
    }
    @RequestMapping("hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam,BindingResult result){
        if(result.hasErrors()){
            return  R.fail("数据查询失败");
        }
        return productService.hots(productHotParam);
    }
    @PostMapping("category/list")
    public  R categoryList(){
        log.info("查询商品类别数据");

        return productService.clist();
    }
    @PostMapping("byCategory")
    public R byCategory(@RequestBody @Validated ProductIdsParam productIdsParam,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  R.fail("类别查询失败");
        }
        return  productService.byCategory(productIdsParam);
    }
    @PostMapping("all")
    public R all(@RequestBody @Validated ProductIdsParam productIdsParam,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  R.fail("类别查询失败");
        }
        return  productService.byCategory(productIdsParam);
    }
    @PostMapping("detail")
    public R detail(@RequestBody @Validated ProductIdParam productIdParam,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  R.fail("商品查询失败");
        }
        return productService.detail(productIdParam.getProductID());
    }
    @PostMapping("pictures")
    public R pictures(@RequestBody @Validated ProductIdParam productIdParam,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  R.fail("商品图片详情查询失败");
        }

        return  productService.pictures(productIdParam.getProductID());
    }

}
