package org.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.UserConstants;
import org.example.parms.UserCheckParam;
import org.example.parms.UserLoginParam;
import org.example.user.mapper.UserMapper;
import org.example.pojo.User;
import org.example.user.service.UserService;
import org.example.utils.MD5Util;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    /**
     * 检查用户是否存在于数据库中，判断是否允许注册
     * */
    @Override
    public R check(UserCheckParam userCheckParam) {

        //参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userCheckParam.getUserName());
        //数据库查询
        Long total = userMapper.selectCount(queryWrapper);
        //查询结果处理
        
        if (total == 0){
            //数据库中不存在,可用
            log.info(" 执行 org.example.impl.UserServiceImpl.check 用户账号检查完毕，结果{}","账号可以使用!");
            return R.ok("账号不存在,可以使用!");
        }else{

        log.info("执行 org.example.user.service.impl.UserServiceImpl.check 用户账号检查完毕，结果{}","账号不可使用!");

        return R.ok("账号已经存在,不可注册!");}
    }

    /***
     * 方法设计流程为：
     *   判断是否注册
     *   密码加密
     *   信息与数据库校对
     *   返回封装结果
     *
     *
     * ***/
    @Override
    public R register(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        Long toal = userMapper.selectCount(queryWrapper);
        if(toal>0){
            log.info("用户已存在");
            return  R.fail("账号已存在");
        }
        /**
         * 加盐，加大md5密码破解难度
         * */
        String encode = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(encode);
        //检查数据是否插入成功
        int rows = userMapper.insert(user);
        if(rows==0){
            return R.fail("注册失败");
        }


        return R.ok("注册成功");
    }
    /**
     * 校验登录结果
     *
     * */

    @Override
    public R login(UserLoginParam userLoginParam) {
        //用户提交密码进行加盐，使用md5重新加密，于数据库保存密码进行校验
        String encode = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_name",userLoginParam.getUserName());
        queryWrapper.eq("password",encode);

        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            log.info("用户不存在");
            return R.fail("账号或者密码错误");
        }

        return R.ok("登录成功",user);
    }
}
