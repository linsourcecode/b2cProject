package org.example.user.controller;


import org.example.parms.AddressListParam;
import org.example.parms.AddressParam;
import org.example.parms.AddressRemoveParam;
import org.example.user.service.AddressService;
import org.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/**
 * 提供用户地址增删查接口
 * */
@RestController
@RequestMapping("user/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    /**
     * 提供用户地址查询接口
     * */
    @ResponseBody
    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return R.fail("参数异常，查询失败");
        }
        return  addressService.list(addressListParam.getUserId());

    }
    /**
     * 提供用户地址保存
     * */
    @ResponseBody
    @PostMapping("save")
    public R save(@RequestBody AddressParam address, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  R.fail("参数异常,失败");
        }
        return  addressService.save(address);
    }
    @ResponseBody
    @PostMapping("remove")
    public R remove(@RequestBody @Validated AddressRemoveParam addressRemoveParam, BindingResult result){

        if (result.hasErrors()){

            return R.fail("参数异常,删除失败!");
        }

        return addressService.remove(addressRemoveParam.getId());
    }
}
