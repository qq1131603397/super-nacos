package com.hz.redis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author： pt
 * @date： 2022/11/28 10:46
 * @discription
 */
@Data
public class Student implements Serializable {

    private String id;

    private String name;

}
