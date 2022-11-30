package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Cart;

/**
 * @author ：林梓龙
 * <p>购物车mapper类</p>
 * @description：TODO
 * @date ：2022/11/19 0019 15:14
 */
@Mapper
public interface CartMapper  extends BaseMapper<Cart> {
}
