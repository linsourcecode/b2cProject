package org.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.parms.AddressParam;
import org.example.pojo.Address;
import org.example.user.mapper.AddressMapper;
import org.example.user.service.AddressService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 此类提供用户地址服务方法
 * */
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    /**提供用户地址查询服务
     * */
    @Override
    public R list(Integer userId) {
        //1,封装查询参数
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);
        //2.结果封装
        R ok = R.ok("查询成功", addressList);



        return ok;
    }
    /**提供用户地址保存服务
     * */
    @Override
    public R save(AddressParam addressParam) {

        Address address = addressParam.getAdd();
        address.setUserId(address.getUserId());

        //1.数据库插入
        int rows = addressMapper.insert(address);

        //2.返回结果处理
        if (rows == 0){
            return R.fail("地址保存失败!");
        }

        log.info("AddressServiceImpl.save业务结束，结果:{}",address);
        //调用查询,返回全部数据!
        return list(address.getUserId());
    }
    /**
     *
     * 根据id 删除用户地址数据
     */
    @Override
    public R remove(Integer id) {

        int rows = addressMapper.deleteById(id);

        if (rows == 0){
            log.info("AddressServiceImpl.remove业务结束，结果:{}","地址删除失败");
            return R.fail("删除地址数据失败!");
        }

        log.info("AddressServiceImpl.remove业务结束，结果:{}","地址删除成功!");

        return R.ok("地址删除成功!");
    }
}
