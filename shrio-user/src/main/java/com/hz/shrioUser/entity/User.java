package com.hz.shrioUser.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author： pt
 * @date： 2022/12/1 9:52
 * @discription
 */
@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -6056125703075132981L;

    @Id
    private Integer id;

    private String account;

    private String password;

    private String username;

}
