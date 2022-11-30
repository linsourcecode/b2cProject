package org.example.parms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ：林梓龙
 * <p>购物车添加参数验证</p>
 * @description：TODO
 * @date ：2022/11/19 0019 14:54
 */
@Data
public class CartSaveParam {

    @JsonProperty("product_id")
    @NotNull
    private Integer productId;
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
}

