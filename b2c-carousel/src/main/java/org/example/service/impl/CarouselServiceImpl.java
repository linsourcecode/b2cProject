package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.CarouselMapper;
import org.example.pojo.Carousel;
import org.example.service.CarouselService;

import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselServiceMapper;
    @Override
    public R list() {
        QueryWrapper<Carousel> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("priority");
        List<Carousel> carousels = carouselServiceMapper.selectList(queryWrapper);
        List<Carousel> collect = carousels.stream().limit(6).collect(Collectors.toList());
        R ok =R.ok(collect);
        log.info("CarouselServiceImpl 执行业务完毕");
        return ok;
    }
}
