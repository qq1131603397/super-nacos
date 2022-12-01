package com.hz.shrioUser.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author： pt
 * @date： 2022/12/1 9:53
 * @discription
 */
@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    private Integer id;

    private String role;

    private String desc;
}
