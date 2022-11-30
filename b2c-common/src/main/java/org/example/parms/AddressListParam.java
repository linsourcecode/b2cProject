package org.example.parms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AddressListParam implements Serializable {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

}
