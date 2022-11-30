package org.example.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.example.clients.OrderClient;
import org.example.parms.PageParam;
import org.example.service.OrderService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * projectName: b2c-store
 * <p>
 * description: 订单业务实现类
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClient orderClient;
    /**
     * 查询订单数据
     *
     * @param pageParam
     * @return
     */
    @Override
    public R list(PageParam pageParam) {

        R r = orderClient.list(pageParam);
        log.info("OrderServiceImpl.list业务结束，结果:{}",r);
        return r;
    }
}
