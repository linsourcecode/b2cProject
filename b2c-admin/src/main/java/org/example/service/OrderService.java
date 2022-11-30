package org.example.service;


import org.example.parms.PageParam;
import org.example.utils.R;

/**
 * projectName: b2c-store
 * <p>
 * description:
 */
public interface OrderService {

    /**
     * 查询订单数据
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);
}
