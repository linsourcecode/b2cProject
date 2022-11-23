package org.example.controller;

import org.apache.commons.lang.StringUtils;
import org.example.parms.ProductHotParam;
import org.example.service.CategoryService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * 实现产品分类别查询功能
 *
 * */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/promo/{categoryName}")
    public R getDetail(@PathVariable String categoryName){
        if(StringUtils.isEmpty(categoryName)){
            return  R.fail("输入类别为空，无法查询数据");
        }
        return  categoryService.getData(categoryName);
    }
    @PostMapping("hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("数据查询失败!");
        }

        return categoryService.hots(productHotParam);
    }
    @GetMapping("list")
    public R list(){

        return  categoryService.list();
    }
}
