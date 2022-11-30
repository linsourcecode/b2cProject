package org.example.service;


import org.example.pojo.AdminUser;
import org.example.pojo.AdminUserParam;

/**
 * projectName: b2c-store
 * <p>
 * description:
 */
public interface AdminUserService {

    /**
     * 登录业务方法
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);
}
