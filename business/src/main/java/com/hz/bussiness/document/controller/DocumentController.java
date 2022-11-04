package com.hz.bussiness.document.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * @author： pt
 * @date： 2022/10/13 13:23
 * @discription
 */
@RestController
@RequestMapping("document")
@Api(value = "Document相关接口", tags = {"文档相关接口"})
public class DocumentController {

    @GetMapping(value = "preview")
    @ApiOperation(value = "文档预览", tags = {"文档相关接口"}, httpMethod = "GET")
    public void reviewPriceData(@ApiParam(name = "filePath", value = "预览文件路径", required = true) @RequestParam("filePath") String filePath, HttpServletResponse response) throws Exception {
        FileInputStream inStream = new FileInputStream(filePath);
        // 设置输出的格式
        response.setContentType("application/pdf");
        OutputStream outputStream = response.getOutputStream();
        int count;
        byte[] buffer = new byte[1024 * 1024];
        while ((count = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, count);
        }
        outputStream.flush();
    }

}
