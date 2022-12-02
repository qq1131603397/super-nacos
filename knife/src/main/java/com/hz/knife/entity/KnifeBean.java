package com.hz.knife.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author： pt
 * @date： 2022/11/22 17:07
 * @discription
 */
@Data
@ApiModel(value = "KnifeBean", description = "Knife测试实体")
public class KnifeBean {

    @ApiModelProperty(value = "用户id", name = "id", required = true, example = "007", allowableValues = "10")
    @Min(value = 1, message = "最小长度为1")
    @Max(value = 16, message = "最大长度为16")
    private String id;

    @ApiModelProperty(value = "用户名,不包含特殊字符", name = "name", required = false, example = "张三", allowableValues = "16")
    private String name;

    @ApiModelProperty(value = "密码,至少包含字母、数字和特殊字符其中两种类型", name = "password", required = false, example = "123456", allowableValues = "8-16")
    private String password;

}
