package org.example.parms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author ：林梓龙
 * <p>收藏查询的传递的实体参数</p>
 * @description：TODO
 * @date ：2022/11/18 0018 20:39
 */
@Data
public class ProductCollectParam {

    @NotEmpty
    private List<Integer> productIds;

}
