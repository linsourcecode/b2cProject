package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.parms.ProductSearchParams;
import org.example.utils.R;

public interface SearchService {
    R search(ProductSearchParams productSearchParams) throws JsonProcessingException;
}
