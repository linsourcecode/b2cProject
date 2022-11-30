package org.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Order;
import org.example.vo.AdminOrderVo;

import java.util.List;

/**
 * projectName: b2c-cloud-store
 *
 * @author: 赵伟风
 * time: 2022/10/21 10:08 周五
 * description: 订单mapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 分页查询数据,返回order封装vo
     * @param offset
     * @param number
     * @return
     */
    List<AdminOrderVo> selectAdminOrders(@Param("offset") int offset, @Param("number")int number);
}
