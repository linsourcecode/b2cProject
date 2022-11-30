package org.example.user.service;

import org.example.parms.UserCheckParam;
import org.example.parms.UserLoginParam;
import org.example.pojo.User;
import org.example.utils.R;


public interface UserService {
    /**
     * 检查账号是否可用
     */

    R check(UserCheckParam userCheckParam);
    R register(User user);

    R login(UserLoginParam userLoginParam);
}
