package org.example.service;


import org.example.parms.CartListParam;
import org.example.parms.PageParam;
import org.example.pojo.User;
import org.example.utils.R;

/**
 * projectName: b2c-store
 * <p>
 * description:
 */
public interface UserService {

    /**
     * 用户的展示业务方法
     * @param pageParam
     * @return
     */
    R userList(PageParam pageParam);

    /**
     * 删除用户数据
     * @param cartListParam
     * @return
     */
    R userRemove(CartListParam cartListParam);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    R userUpdate(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    R save(User user);
}
