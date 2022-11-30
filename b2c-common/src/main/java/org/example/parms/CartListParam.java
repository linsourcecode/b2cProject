package org.example.parms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ：林梓龙
 * <p>用户购物车查询接收参数</p>
 * @description：TODO
 * @date ：2022/11/19 0019 16:25
 */
@Data
public class CartListParam {

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
}

