package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.ProductClient;
import org.example.mapper.CollectMapper;
import org.example.parms.CollectParam;
import org.example.parms.ProductCollectParam;
import org.example.pojo.Collect;
import org.example.service.CollectService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：林梓龙
 * <p>收藏服务实现接口</p>
 * @description：TODO
 * @date ：2022/11/18 0018 16:17
 */
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    ProductClient productClient;
    @Override
    public Object save(CollectParam collectParam) {
        //分解参数
        Integer userId = collectParam.getUserId();
        Integer productId = collectParam.getProductId();
        //数据库查询
        QueryWrapper<Collect> queryWrapper
                = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("product_id",productId);
        Long count = collectMapper.selectCount(queryWrapper);

        if ( count> 0){
            log.info("CollectServiceImpl.save业务结束，结果:{}",count);
            return R.fail("商品已在收藏夹! 无需二次添加!");
        }
        //实体类封装
        Collect collect = new Collect();
        collect.setProductId(productId);
        collect.setUserId(userId);
        collect.setCollectTime(System.currentTimeMillis());
        //数据库插入
        int rows = collectMapper.insert(collect);
        //结果封装
        return R.ok("商品添加收藏成功!");

    }

    @Override
    public R list(Integer userId) {
        log.info("编号为"+userId+"发起用户查询用户收藏信息查询请求");
        QueryWrapper<Collect> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.select("product_id");
        List<Object> objectList= collectMapper.selectObjs(queryWrapper);
        log.info("返回"+objectList.size()+"条记录");
        ProductCollectParam productCollectParam=new ProductCollectParam();
        List<Integer> ids=new ArrayList<>();
        for (Object o : objectList) {
            ids.add(((Integer) o));
        }
        productCollectParam.setProductIds(ids);
        R r = productClient.productIds(productCollectParam);
        log.info("CollectServiceImpl.list调用",r);
        return r;
    }

    /**移除收藏信息
     * */
    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",collect.getUserId());
        queryWrapper.eq("product_id",collect.getProductId());
        int rows = collectMapper.delete(queryWrapper);
        log.info("用户收藏信息移除成功");
        return R.ok("收藏移除成功！");
    }
}
