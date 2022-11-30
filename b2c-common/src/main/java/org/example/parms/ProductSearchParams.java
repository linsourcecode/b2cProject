package org.example.parms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ：林梓龙
 * <p>搜索关键字和分页参数集合</p>
 * @description：TODO
 * @date ：2022/11/18 0018 10:11
 */
@Data
public class ProductSearchParams {
    @JsonProperty("search")
    private String search;
    @JsonProperty("currentPage")
    private int    currentPage = 1;
    @JsonProperty("pageSize")
    private int    pageSize = 15;

    /**
     * 运算分页起始值
     * @return
     */
    public int getFrom(){
        return (currentPage-1)*pageSize;
    }

    /**
     * 返回查询值
     * @return
     */
    public int getSize(){
        return pageSize;
    }
}

