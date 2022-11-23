package com.hz.knife.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author： pt
 * @date： 2022/10/8 16:02
 * @discription 返回实体
 */
@ApiModel(value = "ResultVO", description = "公共返回实体")
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 6543751866024162628L;
    @ApiModelProperty(value = "kind", name = "kind")
    private String kind;
    @ApiModelProperty(value = "结果", name = "result", notes = "true代表成功，false代表失败")
    private boolean result;
    @ApiModelProperty(value = "返回信息", name = "msg", notes = "返回信息，可用于提示")
    private String msg;
    @ApiModelProperty(value = "返回数据", name = "data", notes = "JSON格式")
    private T data;

    public ResultVO() {
        this.result = true;
    }

    public ResultVO(boolean result, String kind) {
        this.result = result;
        this.kind = kind;
    }

    public ResultVO(String kind, String msg) {
        this.result = false;
        this.kind = kind;
        this.msg = msg;
    }

    public ResultVO(boolean result, String kind, String msg) {
        this.result = result;
        this.kind = kind;
        this.msg = msg;
    }

    public ResultVO(boolean result, T data, String kind, String msg) {
        this.result = result;
        this.data = data;
        this.kind = kind;
        this.msg = msg;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

