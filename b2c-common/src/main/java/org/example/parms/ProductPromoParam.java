package org.example.parms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPromoParam {
    @NotNull
    @JsonProperty("categoryName")
    private String categoryName;

}
