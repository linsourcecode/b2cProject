package org.example.service;

import org.example.parms.CartSaveParam;
import org.example.pojo.Cart;
import org.example.utils.R;

import java.util.List;

public interface CartService {
    R save(CartSaveParam cartSaveParam);

    R list(Integer userId);

    R update(Cart cart);

    R remove(Cart cart);

    void clearIds(List<Integer> cartIds);

    R check(Integer productId);
}
