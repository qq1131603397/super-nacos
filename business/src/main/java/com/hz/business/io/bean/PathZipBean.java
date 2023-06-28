package com.hz.business.io.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author： pt
 * @date： 2023/6/28 9:42
 * @discription
 */
@Data
@AllArgsConstructor
public class PathZipBean implements Serializable {
    /**
     * 源文件
     */
    private String source;

    /**
     * 目的文件夹
     */
    private String target;

    /**
     * 目的文件名
     */
    private String targetName;
}