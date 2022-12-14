package org.example.controller;

import org.example.parms.CollectParam;
import org.example.pojo.Collect;
import org.example.service.CollectService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：林梓龙
 * <p>收藏服务控制方法</p>
 * @description：TODO
 * @date ：2022/11/18 0018 16:16
 */
@RestController
@RequestMapping("collect")
public class CollectController {

    @Autowired
    private CollectService collectService;


    @PostMapping("save")
    public Object save(@RequestBody CollectParam collectParam){

        return collectService.save(collectParam);
    }

    @PostMapping("list")
    public R list(@RequestBody Collect collect){

        return  collectService.list(collect.getUserId());
    }
    /**
     * 移除收藏接口
     * */
    @PostMapping("remove")
    public R remove(@RequestBody Collect collect){

        return  collectService.remove(collect);

    }



}
