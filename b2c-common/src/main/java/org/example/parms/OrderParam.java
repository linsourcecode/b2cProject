package org.example.parms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.vo.CartVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：林梓龙
 * <p>校验订单参数</p>
 * @description：TODO
 * @date ：2022/11/19 0019 18:40
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderParam implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;

}