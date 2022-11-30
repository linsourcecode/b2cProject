package org.example.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.rowset.serial.SerialBlob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName("address")
@NoArgsConstructor
public class Address implements Serializable {
    public static final Long serialVersionUID=1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String linkman;
    private String phone;
    private String address;
    @JsonProperty("user_id")
    @TableField("user_id")
    private Integer userId;
}
