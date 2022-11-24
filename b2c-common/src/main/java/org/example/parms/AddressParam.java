package org.example.parms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.pojo.Address;


@Data
public class AddressParam {

    @JsonProperty("user_id")
    private Integer userId;
    private Address add;
}
