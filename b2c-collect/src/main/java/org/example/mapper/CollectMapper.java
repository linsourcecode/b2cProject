package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Collect;

/**
 * projectName: b2c-cloud-store
 *
 * @author: 赵伟风
 * time: 2022/10/20 14:51 周四
 * description: 收藏mapper
 */
@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
}
