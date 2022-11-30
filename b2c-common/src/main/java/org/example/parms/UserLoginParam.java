package org.example.parms;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *
 * 用于登录验证类
 * */
@Data
public class UserLoginParam {
    @NotBlank
    String userName;
    @NotBlank
    String password;

}
