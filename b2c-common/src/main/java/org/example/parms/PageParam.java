package org.example.parms;

import lombok.Data;

/**
 * @author ：林梓龙
 * <p>分页参数</p>
 * @description：TODO
 * @date ：2022/11/18 0018 10:14
 */
@Data
public class PageParam {

    private int    currentPage = 1;
    private int    pageSize = 15;
}

