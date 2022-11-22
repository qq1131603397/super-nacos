package com.hz.business.document.controller;

import com.alibaba.fastjson.JSON;
import com.hz.business.document.entity.SwaggerBean;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author： pt
 * @date： 2022/11/22 15:45
 * @discription
 */
@RestController
@Api(tags = {"Swagger测试类"})
@RequestMapping("swagger")
public class SwaggerTestController {

    @ApiOperation(value = "测试GET请求", notes = "单个参数")
    @GetMapping(value = "getOne", produces = "application/json; charset=utf-8")
    public String getTest(@ApiParam(name = "name", value = "用户名称", required = false, example = "张三") @RequestParam("name") String name) {
        if (name == null || "".equals(name)) {
            return "用户名为空";
        }
        return "用户名：" + name;
    }

    @ApiOperation(value = "测试GET请求", notes = "多个参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType =
                    "String", required = true, defaultValue = "admin", paramType = "query", example = "admin"),
            @ApiImplicitParam(name = "password", value = "密码", dataType =
                    "String", required = true, defaultValue = "ADmin@123", paramType = "query", example = "ADmin@123")
    })
    @GetMapping(value = "getMore", produces = "application/json; charset=utf-8")
    public String getTest2(@RequestParam("name") String name, @RequestParam("password") String password) {
        return "用户名：" + name + "   密码：" + password;
    }

    @ApiOperation(value = "测试GET请求", notes = "路径参数")
    @GetMapping(value = "getPath/{path}", produces = "application/json; charset=utf-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", dataType =
                    "String", required = true, defaultValue = "test", paramType = "path", example = "test")
    })
    public String getTest3(@PathVariable("path") String path) {
        return "路径：" + path;
    }

    @ApiOperation(value = "测试POST请求", notes = "实体类")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "参数错误"),
            @ApiResponse(code = 500, message = "服务器异常"),
    })
    @PostMapping(value = "postTest", produces = "application/json; charset=utf-8")
    public String postTest(@RequestBody SwaggerBean swaggerBean) {
        return JSON.toJSONString(swaggerBean);
    }
}
