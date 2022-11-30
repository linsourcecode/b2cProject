package org.example;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：林梓龙
 * <p>订单发送商品服务的实体</p>
 * @description：TODO
 * @date ：2022/11/19 0019 19:38
 */
@Data
public class OrderToProduct implements Serializable {

    public static final Long serialVersionUID = 1L;


    private Integer productId;
    private Integer num;

}
