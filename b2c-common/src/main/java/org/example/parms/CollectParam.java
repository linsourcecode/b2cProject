package org.example.parms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ：林梓龙
 * <p>收藏验证单的实体类</p>
 * @description：TODO
 * @date ：2022/11/18 0018 16:10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CollectParam {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
}

