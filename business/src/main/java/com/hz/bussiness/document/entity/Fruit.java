package com.hz.bussiness.document.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author： pt
 * @date： 2022/9/1 14:10
 * @discription
 */
@Data
@Entity
public class Fruit {

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")//这个是hibernate的注解
    @GeneratedValue(generator = "systemUUID")  //使用uuid的生成策略
    @Column(length = 32)
    private String id;

    /**
     * 物品种类
     */
    private String category;

    /**
     * 水果名称
     */
    private String fruit;

    /**
     * 水果颜色
     */
    private String color;

    /**
     * 水果产期
     */
    private Long produceDate;

}
