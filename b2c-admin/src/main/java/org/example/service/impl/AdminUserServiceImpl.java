package org.example.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.UserConstants;
import org.example.mapper.AdminUserMapper;
import org.example.pojo.AdminUser;
import org.example.pojo.AdminUserParam;
import org.example.service.AdminUserService;
import org.example.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * projectName: b2c-store
 * <p>
 * description: 用户业务实现类
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 登录业务方法
     *
     * @param adminUserParam
     * @return
     */
    @Override
    public AdminUser login(AdminUserParam adminUserParam) {

        QueryWrapper<AdminUser> queryWrapper
                   = new QueryWrapper<>() ;

        queryWrapper.eq("user_account",adminUserParam.getUserAccount());
        //密码
        queryWrapper.eq("user_password", MD5Util.encode(adminUserParam.getUserPassword()+ UserConstants.USER_SLAT));

        AdminUser user = adminUserMapper.selectOne(queryWrapper);
        log.info("AdminUserServiceImpl.login业务结束，结果:{}",user);
        return user;
    }
}
