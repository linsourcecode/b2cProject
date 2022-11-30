package org.example.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.ProductClient;
import org.example.mapper.OrderMapper;
import org.example.parms.OrderParam;
import org.example.parms.ProductCollectParam;
import org.example.parms.ProductNumberParam;
import org.example.pojo.Order;
import org.example.pojo.Product;
import org.example.service.OrderService;
import org.example.utils.R;
import org.example.vo.CartVo;
import org.example.vo.OrderVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
public class OrderServiceImpl  extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private ProductClient productClient;

    /**
     * 消息队列发送
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 订单保存业务
     * 库存和购物车使用mq异步,避免分布式事务!
     * @param orderParam
     * @return
     */
    @Transactional //添加事务
    @Override
    public Object save(OrderParam orderParam) {

        //修改清空购物车的参数
        List<Integer> cartIds = new ArrayList<>();
        //修改批量插入数据库的参数
        List<Order>  orderList = new ArrayList<>();
        //商品修改库存参数集合
        List<ProductNumberParam>  productNumberParamList  =
                                             new ArrayList<>();

        Integer userId = orderParam.getUserId();
        List<CartVo> products = orderParam.getProducts();
        //封装order实体类集合
        //统一生成订单编号和创建时间
        //使用时间戳 + 做订单编号和事件
        long ctime = System.currentTimeMillis();

        for (CartVo cartVo : products) {
            cartIds.add(cartVo.getId()); //进行购物车订单保存
            //订单信息保存
            Order order = new Order();
            order.setOrderId(ctime);
            order.setUserId(userId);
            order.setOrderTime(ctime);
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order); //添加用户信息

            //修改信息存储
            ProductNumberParam productNumberParam = new ProductNumberParam();
            productNumberParam.setProductId(cartVo.getProductID());
            productNumberParam.setProductNum(cartVo.getNum());
            productNumberParamList.add(productNumberParam); //添加集合
        }
        //批量数据插入
        this.saveBatch(orderList); //批量保存

        //修改商品库存 [product-service] [异步通知]
        /**
         *  交换机: topic.ex
         *  routingkey: sub.number
         *  消息: 商品id和减库存数据集合
         */
        rabbitTemplate.convertAndSend("topic.ex","sub.number",productNumberParamList);
        //清空对应购物车数据即可 [注意: 不是清空用户所有的购物车数据] [cart-service] [异步通知]
        /**
         * 交换机:topic.ex
         * routingkey: clear.cart
         * 消息: 要清空的购物车id集合
         */
        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);

        R ok = R.ok("订单生成成功!");
        log.info("OrderServiceImpl.save业务结束，结果:{}",ok);
        return ok;
    }

    /**
     * 订单数据查询业务
     *
     */
     @Override
    public R list(Integer userId) {
         QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
         queryWrapper.eq("user_id",userId);
         List<Order> list = list(queryWrapper);

         //分组
         Map<Long, List<Order>> orderMap = list.stream().collect(Collectors.groupingBy(Order::getOrderId));

         //查询商品数据
         List<Integer> productIds = list.stream().map(Order::getProductId).collect(Collectors.toList());

         ProductCollectParam productCollectParam = new ProductCollectParam();
         productCollectParam.setProductIds(productIds);
         List<Product> productList = productClient.cartList(productCollectParam);

         Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

         //结果封装
         List<List<OrderVo>> result = new ArrayList<>();

         //遍历订单项集合
         for (List<Order> orders : orderMap.values()) {
             //封装每一个订单
             List<OrderVo> orderVos = new ArrayList<>();
             for (Order order : orders) {
                 OrderVo orderVo = new OrderVo();
                 BeanUtils.copyProperties(order,orderVo);
                 Product product = productMap.get(order.getProductId());
                 orderVo.setProductName(product.getProductName());
                 orderVo.setProductPicture(product.getProductPicture());
                 orderVos.add(orderVo);
             }

             result.add(orderVos);
         }

         R r = R.ok("订单数据获取成功!", result);
         log.info("OrderServiceImpl.list业务结束，结果:{}",r);
         return r;

     }

    /**
     * 检查订单是否包含要删除的商品
     *
     * @param productId
     * @return
     */
    //@Override
    public Object check(Integer productId) {

        QueryWrapper<Order> queryWrapper
                  = new QueryWrapper<>();

        queryWrapper.eq("product_id",productId);

        Long total = baseMapper.selectCount(queryWrapper);

        if (total == 0){

            return R.ok("订单中不存在要删除的商品!");
        }

        return R.fail("订单中存在要删除的商品,删除失败!");
    }

    /**
     * 分页查询订单数据
     *
     * @param pageParam
     * @return
     */
  /*  @Override
    public Object adminList(PageParam pageParam) {

        int offset = (pageParam.getCurrentPage()-1)*pageParam.getPageSize();
        int number = pageParam.getPageSize();

        //查询数量
        Long total = orderMapper.selectCount(null);
        //自定义查询
        List<AdminOrderVo> adminOrderVoList = orderMapper.selectAdminOrders(offset,number);


        return R.ok("查询成功",adminOrderVoList,total);
    }*/
}
