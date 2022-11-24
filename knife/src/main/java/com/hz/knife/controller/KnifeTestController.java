package com.hz.knife.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
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
    public ResultVO<JSONObject> getTest(@ApiParam(name = "name", value = "用户名称", required = false) @RequestParam("name") String name) {
        if (name == null || "".equals(name)) {
            return new ResultVO<>(false, null, "用户名为空");
        }
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        return new ResultVO<>(true, obj, null, null);
    }

    @ApiOperation(value = "测试GET请求", notes = "多个参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType =
                    "String", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType =
                    "String", required = true, paramType = "query")
    })
    @GetMapping(value = "getMore", produces = "application/json; charset=utf-8")
    public ResultVO<JSONObject> getTest2(@RequestParam("name") String name, @RequestParam("password") String password) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("password", password);
        return new ResultVO<>(true, obj, null, null);
    }

    @ApiOperation(value = "测试GET请求", notes = "路径参数")
    @GetMapping(value = "getPath/{path}", produces = "application/json; charset=utf-8")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "路径", dataType =
                    "String", required = true, paramType = "path")
    })
    public ResultVO<JSONObject> getTest3(@PathVariable("path") String path) {
        JSONObject obj = new JSONObject();
        obj.put("path", path);
        return new ResultVO<>(true, obj, null, null);
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
    @PostMapping(value = "postTest", produces = "application/json; charset=utf-8")
    public ResultVO<KnifeBean> postTest(@Validated @RequestBody KnifeBean swaggerBean) {
        return new ResultVO<>(true, swaggerBean, null, null);
    }
}
