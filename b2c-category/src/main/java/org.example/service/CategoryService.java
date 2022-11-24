package org.example.service;

import org.example.parms.ProductHotParam;
import org.example.pojo.Category;
import org.example.utils.R;

import java.util.List;

public interface CategoryService {

    R getData(String categoryName);

    R hots(ProductHotParam productHotParam);

   R list();
}
