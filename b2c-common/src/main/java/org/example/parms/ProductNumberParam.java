package org.example.parms;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ：林梓龙
 * <p>商品库存表</p>
 * @description：TODO
 * @date ：2022/11/19 0019 19:26
 */
@Getter
@Setter
@Data
public class ProductNumberParam {

    //商品id
    private Integer productId;
    //购买数量
    private Integer productNum;
}

