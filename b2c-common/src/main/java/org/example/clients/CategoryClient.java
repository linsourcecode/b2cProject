package org.example.clients;


import org.example.parms.PageParam;
import org.example.parms.ProductHotParam;
import org.example.pojo.Category;
import org.example.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * projectName: b2c-store
 * <p>
 * description: 类别的调用接口
 */
@FeignClient("category-service")
public interface CategoryClient {

    @GetMapping("/category/promo/{categoryName}")
    R getDetail(@PathVariable String categoryName);

    @PostMapping("/category/hots")
    R hots(@RequestBody ProductHotParam productHotParam);

    @GetMapping("/category/list")
    R list();

    @PostMapping("/category/admin/list")
    R adminPageList(@RequestBody PageParam pageParam);

    @PostMapping("/category/admin/save")
    R adminSave(@RequestBody Category category);

    @PostMapping("/category/admin/remove")
    R adminRemove(@RequestBody Integer categoryId);

    @PostMapping("/category/admin/update")
    R adminUpdate(@RequestBody Category category);
    @PostMapping("/category/admin/update")
    R getDetails(String categoryName);
}
