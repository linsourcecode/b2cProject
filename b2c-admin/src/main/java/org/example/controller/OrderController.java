package org.example.controller;


import org.example.parms.PageParam;
import org.example.service.OrderService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * projectName: b2c-store
 * <p>
 * description: order的controller
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public R list(PageParam pageParam){

        return orderService.list(pageParam);
    }
}
