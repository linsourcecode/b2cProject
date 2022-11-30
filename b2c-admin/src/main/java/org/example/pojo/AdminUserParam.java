package org.example.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author ：林梓龙
 * <p>登录验证校验实体类</p>
 * @description：TODO
 * @date ：2022/11/20 0020 13:21
 */
@Data
public class AdminUserParam {

    @Length(min = 6)
    private String userAccount; //账号
    @Length(min = 6)
    private String userPassword; //密码
    @NotBlank
    private String verCode;  //验证码

}
