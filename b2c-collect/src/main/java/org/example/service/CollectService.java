package org.example.service;

import org.example.parms.CollectParam;
import org.example.pojo.Collect;
import org.example.utils.R;

public interface CollectService {
    Object save(CollectParam collectParam);
    /**
     * 根据用户id查询收藏信息集合
     * */
    R list(Integer userId);

    R remove(Collect collect);
}
