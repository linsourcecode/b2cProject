package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.OrderToProduct;
import org.example.parms.ProductHotParam;
import org.example.parms.ProductIdsParam;
import org.example.parms.ProductSearchParams;
import org.example.pojo.Product;
import org.example.utils.R;

import java.util.List;

public interface ProductService {
    R promo(String categoryName);

    R hots(ProductHotParam productHotParam);

    R clist();

    R byCategory(ProductIdsParam productIdsParam);

    R detail(Integer productID);

    R pictures(Integer productID);

    List<Product>  allList();

    R search(ProductSearchParams productSearchParams) throws JsonProcessingException;

    R ids(List<Integer> productIds);

    List<Product> cartList(List<Integer> productIds);

    void subNumber(List<OrderToProduct> orderToProducts);

    // R hots(ProductHotParam productHotParam);
}
