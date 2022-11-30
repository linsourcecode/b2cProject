package org.example.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：林梓龙
 * <p>商品信息的模糊描述</p>
 * @description：TODO
 * @date ：2022/11/17 0017 20:23
 */
@Data
@NoArgsConstructor
public class ProductDoc extends Product {

    /**
     * 用于模糊查询字段,由商品名,标题和描述组成
     */
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(),product.getProductName(),
                product.getCategoryId(),product.getProductTitle(),
                product.getProductIntro(),product.getProductPicture(),
                product.getProductPrice(),product.getProductSellingPrice(),
                product.getProductNum(),product.getProductSales());
        this.all = product.getProductName()+product.getProductTitle()+product.getProductIntro();
    }
}
