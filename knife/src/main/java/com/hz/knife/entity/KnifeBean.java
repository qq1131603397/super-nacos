package com.hz.knife.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author： pt
 * @date： 2022/11/22 17:07
 * @discription
 */
@Data
@ApiModel(value = "KnifeBean", description = "Knife测试实体")
public class KnifeBean {

    @ApiModelProperty(value = "用户id", name = "id", required = true, example = "007", notes = "长度16位以内")
    private String id;

    @ApiModelProperty(value = "用户名", name = "name", required = false, example = "张三", notes = "长度16位以内，且不包含特殊字符")
    private String name;

    @ApiModelProperty(value = "密码", name = "password", required = false, example = "123456", notes = "长度在8-16位之中，且至少包含字母、数字和特殊字符其中两种类型")
    private String password;

}
