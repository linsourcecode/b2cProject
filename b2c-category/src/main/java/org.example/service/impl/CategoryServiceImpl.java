package org.example.service.impl;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.CategoryMapper;
import org.example.parms.ProductHotParam;
import org.example.pojo.Category;
import org.example.service.CategoryService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    /**返回本类产品
     * */
    @Override
    public R getData(String categoryName) {
        //参数判断
        if (StringUtils.isEmpty(categoryName)){
            //如果没有默认类型,给一个手机类型
            categoryName = "phone";
        }
        //数据库查询
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",categoryName);
        Category category = categoryMapper.selectOne(queryWrapper);
        //返回对象
        log.info("CategoryServiceImpl.detail业务结束，结果:{}",category);
        return R.ok("类别查询成功",category.getCategoryId());
    }
    /**查询分类热门产品
     * */

    @Override
    public R hots(ProductHotParam productHotParam) {
        //封装查询
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("category_name",productHotParam.getCategoryName());
        queryWrapper.select("category_id");
        //查询数据库
        List<Object> ids=categoryMapper.selectObjs(queryWrapper);
        R ok = R.ok("类别集合查询成功",ids);
        return ok ;
    }
    /**查询类别信息
     * */
    @Override
    public R list() {
        List<Category> categoryList=categoryMapper.selectList(null);
        R ok = R.ok("类别信息查询成功",categoryList);

        return ok;
    }
}
// SELECT categoryId, categoryName FROM store_category.category;