package org.example.user.service;

import org.example.parms.AddressParam;
import org.example.utils.R;

public interface AddressService {
    R list(Integer userId);
    R save(AddressParam address);
    R remove(Integer id);
}
