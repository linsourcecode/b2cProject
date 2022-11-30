package org.example.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.pojo.Address;

import javax.validation.constraints.NotNull;

/**
 * projectName: b2c-store
 * <p>
 * description: 地址接收值的param
 */
@Data
public class AddressParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    private Address add;
}
