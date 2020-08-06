package com.offcn.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class People {
    @ApiModelProperty(value="用户id")
    private Integer pid;
    @ApiModelProperty(value="用户名")
    private String pname;
    @ApiModelProperty(value="用户身份证")
    private String countryid;

}