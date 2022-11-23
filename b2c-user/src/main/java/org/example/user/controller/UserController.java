package org.example.user.controller;

import org.example.parms.UserCheckParam;
import org.example.parms.UserLoginParam;
import org.example.pojo.User;
import org.example.user.service.UserService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**前端服务的控制类
 * **/
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    /** 检查账号是否存在
    * */
    @ResponseBody
    @PostMapping("check")
    public R check(@RequestBody @Valid UserCheckParam userCheckParam, BindingResult bindingResult){
        //校验结果
        boolean b=bindingResult.hasErrors();

        if(b){
            return R.fail("账号为null，不可使用");
        }


        return userService.check(userCheckParam);

    }
    /** 注册账号
     * */
    @ResponseBody
    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result){

        if (result.hasErrors()){
            //如果存在异常,证明请求参数不符合注解要求
            return  R.fail("参数异常,不可注册!");
        }

        return userService.register(user);
    }
    /**
     * 登录接口
     * */
    @ResponseBody
    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result){

        if (result.hasErrors()){
            //如果存在异常,证明请求参数不符合注解要求
            return  R.fail("参数异常,不可登录!");
        }

        return userService.login(userLoginParam);
    }
}
