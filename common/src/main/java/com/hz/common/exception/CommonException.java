package com.hz.common.exception;

/**
 * @author： pt
 * @date： 2022/10/14 13:04
 * @discription
 */
public class CommonException extends Exception {

    /**
     * 异常信息
     */
    private String message;

    public CommonException(String message) {
        super(message);
        this.message = message;
    }
}