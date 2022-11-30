package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName("carousel")
@NoArgsConstructor
public class Carousel  implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("carousel_id")
    private Integer carouselId;
    @JsonProperty("img_path")
    private String  imgPath;
    private String  describes;
    @JsonProperty("product_id")
    private Integer productId;
    //优先级
    private Integer priority;

}
