package org.example.parms;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ProductHotParam {
    @NotEmpty
    private List<String> categoryName;
}
