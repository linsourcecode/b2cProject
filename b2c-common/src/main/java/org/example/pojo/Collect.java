package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：林梓龙
 * <p>收藏服务实体类</p>
 * @description：TODO
 * @date ：2022/11/18 0018 16:08
 */
@Data
public class Collect implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Long    collectTime;
}

