package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.example.OrderToProduct;
import org.example.clients.CategoryClient;
import org.example.clients.SearchClient;
import org.example.mapper.PictureMapper;
import org.example.mapper.ProductMapper;
import org.example.parms.ProductHotParam;
import org.example.parms.ProductIdsParam;
import org.example.parms.ProductSearchParams;
import org.example.pojo.Picture;
import org.example.pojo.Product;
import org.example.service.ProductService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {
 @Autowired
 private ProductMapper productMapper;
 @Autowired
 private CategoryClient categoryClient;
 @Autowired
 private PictureMapper pictureMapper;
 @Autowired
 private SearchClient searchClient;


 @Override
 public R promo(String categoryName) {

    R r = categoryClient.getDetail(categoryName);

    if (r.getCode().equals(R.FAIL_CODE)) {
        log.info("ProductServiceImpl.promo业务结束，结果:{}","类别查询失败!");
        return r;
    }
    log.info("返回数据："+r.getData());
    // 类别服务中 data = category --- feign {json}  ----- product服务 LinkedHashMap jackson


    //封装查询参数
    QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("category_id",String.valueOf(r.getData()));
    queryWrapper.orderByDesc("product_sales");

    IPage<Product> page = new Page<>(1,7);

    //返回的是包装数据! 内部有对应的商品集合,也有分页的参数 例如: 总条数 总页数等等
    page = productMapper.selectPage(page, queryWrapper);

    List<Product> productList = page.getRecords(); //指定页的数据
    long total = page.getTotal(); //获取总条数

    log.info("ProductServiceImpl.promo业务结束，结果:{}",productList);

    return R.ok("数据查询成功",productList);
}

 @Override
 public R pictures(Integer productID) {
     QueryWrapper<Picture> queryWrapper=new QueryWrapper<>();
     queryWrapper.eq("product_id",productID);
     List<Picture> pictureList = pictureMapper.selectList(queryWrapper);
     R ok = R.ok(pictureList);
     log.info("图片业务查询结束",ok);

     return ok;
    }

    /**
 * 多类别热门商品查询 根据类别名称集合! 至多查询7条!
 *   1. 调用类别服务
 *   2. 类别集合id查询商品
 *   3. 结果集封装即可
 * @param productHotParam 类别名称集合
 * @return r
 */

    @Override
    public R hots(ProductHotParam productHotParam) {

    R r = categoryClient.hots(productHotParam);

    if(r.getCode().equals(R.FAIL_CODE)){
        log.info("ProductServiceImpl.hots业务结束，结果:{}",r.getMsg());
        return r;
    }

    List<Object> ids = (List<Object>) r.getData();

    //进行商品数据查询
    QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
    queryWrapper.in("category_id",ids);
    queryWrapper.orderByDesc("product_sales");

    IPage<Product> page = new Page<>(1,7);

    page = productMapper.selectPage(page,queryWrapper);

    List<Product> records = page.getRecords();

    R ok = R.ok("多类别热门商品查询成功!", records);

    log.info("ProductServiceImpl.hots业务结束，结果:{}",ok);

    return ok;
}

 @Override
 public R clist() {
   R r = categoryClient.list();
   log.info("查询类别商品的集合");
   return r;
 }

 @Override
 public R byCategory(ProductIdsParam productIdsParam) {
   List<Integer> categoryID = productIdsParam.getCategoryID();
   QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
   if(!categoryID.isEmpty()){
     queryWrapper.in("category_id",categoryID);
   }
   IPage<Product> page=new Page<>(productIdsParam.getCurrentPage(),productIdsParam.getPageSize());
   page = productMapper.selectPage(page,queryWrapper);
   R ok = R.ok("查询成功",page.getRecords(),page.getTotal());


  return ok;
}
    /**
     * 收藏展示接口
     * */
    @Override
    public R ids(List<Integer> productIds) {
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("product_id",productIds);
        for (Integer productId : productIds) {
            log.info("结果为:"+productId);
        }
        List<Product> productList = productMapper.selectBatchIds(productIds);

        R r=R.ok("类别信息查询成功",productList);
        log.info("收藏接口响应成功",productList);
        return r;
    }

 /*   @Override
    public R search(ProductSearchParams productSearchParams) throws JsonProcessingException {
        R r = searchClient.searchProduct(productSearchParams);
        log.info("商品搜索服务");
        return r;
    }
*/
    @Override
    public R detail(Integer productID) {
        Product product=productMapper.selectById(productID);
        R ok = R.ok(product);
        log.info("详细业务查询结束",ok);
        log.info("库存数为"+product.getProductNum());
        return ok;
    }
    /**
     *
     *搜索服务调用，获取全部数据
     *
     * @return*/

    @Override
    public List<Product> allList() {

        List<Product> products = productMapper.selectList(null);
        log.info("");
        return products;
    }

    @Override
    public R search(ProductSearchParams productSearchParams) throws JsonProcessingException {
        return null;
    }

    /**
     * 查询用户的购物车数据
     * */
    @Override
    public List<Product> cartList(List<Integer> productIds) {
        QueryWrapper<Product> productQueryWrapper=new QueryWrapper<>();
        productQueryWrapper.in("product_id",productIds);
        List<Product> productList = productMapper.selectList(productQueryWrapper);

        return productList;
    }

    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {
        //将集合转成map  productId orderToProduct
        Map<Integer, OrderToProduct> map = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));
        //获取商品的id集合
        Set<Integer> productIds = map.keySet();
        //查询集合对应的商品信息
        List<Product> productList = productMapper.selectBatchIds(productIds);
        //修改商品信息
        for (Product product : productList) {
            Integer num = map.get(product.getProductId()).getNum();
            product.setProductNum(product.getProductNum() - num); //减库存
            product.setProductSales(product.getProductSales()+num); //添加销售量
        }
        //批量更新
        this.updateBatchById(productList);
        log.info("ProductServiceImpl.subNumber业务结束，结果:库存和销售量的修改完毕");
    }
}
