package com.hz.knife.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import com.hz.knife.entity.KnifeBean;
import com.hz.knife.entity.ResultVO;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author： pt
 * @date： 2022/11/22 15:45
 * @discription
 */
@RestController
@Api(tags = {"Knife测试类"})
@ApiSupport(author = "pt")
@RequestMapping("knife")
public class KnifeTestController {

    @ApiOperation(value = "测试GET请求", notes = "单个参数")
    @GetMapping(value = "getOne", produces = "application/json; charset=utf-8")
    public KnifeBean getTest(@ApiParam(name = "name", value = "用户名称", required = false) @RequestParam("name") String name) {
        if (name == null || "".equals(name)) {
            return null;
        }
        KnifeBean bean = new KnifeBean();
        bean.setName(name);
        return bean;
    }

    @ApiOperation(value = "测试GET请求", notes = "多个参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType =
                    "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType =
                    "String", required = true, paramType = "query")
    })
    @GetMapping(value = "getMore", produces = "application/json; charset=utf-8")
    public ResultVO<KnifeBean> getTest2(KnifeBean swaggerBean) {
        KnifeBean bean = new KnifeBean();
        bean.setName(swaggerBean.getName());
        bean.setPassword(swaggerBean.getPassword());
        return new ResultVO<>(true, bean, null, null);
    }

    @ApiOperation(value = "测试GET请求", notes = "路径参数")
    @GetMapping(value = "getPath/{path}", produces = "application/json; charset=utf-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", dataType =
                    "String", required = true, paramType = "path")
    })
    public ResultVO<String> getTest3(@PathVariable("path") String path) {
        return new ResultVO<>(true, path, null, null);
    }

    @ApiOperation(value = "测试POST请求", notes = "实体类")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 201, message = "已创建"),
            @ApiResponse(code = 400, message = "错误请求"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止"),
            @ApiResponse(code = 404, message = "未找到"),
            @ApiResponse(code = 500, message = "服务器内部错误"),
            @ApiResponse(code = 503, message = "服务不可用"),
    })
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "result", value = "事件结果(true：成功；false：失败)", example = "true", dataTypeClass = Boolean.class),
            @DynamicParameter(name = "data", value = "返回数据", dataTypeClass = KnifeBean.class),
            @DynamicParameter(name = "kind", value = "类型", dataTypeClass = String.class),
            @DynamicParameter(name = "msg", value = "返回消息", dataTypeClass = String.class),
    })
    @PostMapping(value = "postTest", produces = "application/json; charset=utf-8")
    public ResultVO<KnifeBean> postTest(@Validated @RequestBody KnifeBean swaggerBean) {
        return new ResultVO<>(true, swaggerBean, null, null);
    }
}
