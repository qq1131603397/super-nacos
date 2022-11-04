package com.hz.bussiness.common.entity;

import java.io.Serializable;

/**
 * @author： pt
 * @date： 2022/10/8 16:02
 * @discription
 */
public class ResultVO implements Serializable {
    private static final long serialVersionUID = 6543751866024162628L;
    private String kind;
    private boolean result;
    private String msg;
    private Object data;

    public ResultVO(boolean result, String kind, Object data, String msg) {
        this.kind = kind;
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVO success(String kind, Object data, String msg) {
        return new ResultVO(true, kind, data, msg);
    }

    public static ResultVO success(Object data) {
        return new ResultVO(true, null, data, null);
    }

    public static ResultVO failure(String kind, Object data, String msg) {
        return new ResultVO(false, kind, data, msg);
    }

    public static ResultVO failure(Object data) {
        return new ResultVO(false, null, data, null);
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

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultVO(boolean result, Object data, String kind, String msg) {
        this.result = result;
        this.data = data;
        this.kind = kind;
        this.msg = msg;
    }
}

