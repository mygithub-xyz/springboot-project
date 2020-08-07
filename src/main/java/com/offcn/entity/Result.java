package com.offcn.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 全局返回结果
 * 是否成功 success: true false
 * 信息 message
 */
@ApiModel
public class Result implements Serializable {
    @ApiModelProperty(value="返回结果true or false")
    private boolean success;
    @ApiModelProperty(value="返回的提示信息message")
    private String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}