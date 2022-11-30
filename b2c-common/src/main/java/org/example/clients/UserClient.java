package org.example.clients;


import org.example.parms.CartListParam;
import org.example.parms.PageParam;
import org.example.pojo.User;
import org.example.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * projectName: b2c-store
 * <p>
 * description: 用户的客户端
 */
@FeignClient("user-service")
public interface UserClient {

    @PostMapping("/user/admin/list")
    R adminListPage(@RequestBody PageParam pageParam);

    @PostMapping("/user/admin/remove")
    R adminRemove(@RequestBody CartListParam cartListParam);

    @PostMapping("/user/admin/update")
    R adminUpdate(@RequestBody User user);

    @PostMapping("/user/admin/save")
    R adminSave(@RequestBody User user);
}
