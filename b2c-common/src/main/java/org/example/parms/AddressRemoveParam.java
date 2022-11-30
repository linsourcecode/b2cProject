package org.example.parms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressRemoveParam {

    @NotNull

    private Integer id;
}
