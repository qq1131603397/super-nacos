package com.hz.business.document.controller;

import com.alibaba.excel.EasyExcel;
import com.hz.business.document.service.EasyExcelService;
import com.hz.business.document.util.ColumnWidthStyleStrategy;
import com.hz.business.document.util.EasyExcelUtil;
import com.hz.business.document.util.MergeStrategy;
import com.hz.business.document.bean.dto.FruitExportDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.portable.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author： pt
 * @date： 2022/10/13 14:08
 * @discription
 */
@RestController
@RequestMapping("easyExcel")
@Api(value = "EasyExcel相关接口", tags = {"文档相关接口"})
public class EasyExcelController {

    @Autowired
    private EasyExcelService easyExcelService;

    @GetMapping("export")
    @ApiOperation(value = "测试EasyExcel导出", tags = {"文档相关接口"}, httpMethod = "GET")
    public void export() {
        String fileName = "测试.xls";
        List<FruitExportDto> exportList = easyExcelService.getFruitData();
        EasyExcel.write(fileName, FruitExportDto.class)
                .registerWriteHandler(EasyExcelUtil.getCommonExcelStyle())
                .registerWriteHandler(new ColumnWidthStyleStrategy())
                .registerWriteHandler(new MergeStrategy(1, easyExcelService.getMergeMap()))
                .sheet("sheet")
                .doWrite(exportList);
    }

    @GetMapping(value = "exportNew", produces = "application/json; charset=utf-8")
    @ApiOperation(value = "测试EasyExcel导出", tags = {"文档相关接口"}, httpMethod = "GET")
    public void exportNew(HttpServletResponse response) throws IOException {
        //写入数据
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + "abc.xlsx");
        List<FruitExportDto> exportList = easyExcelService.getFruitData();
        // 根据用户传入字段 假设我们要忽略 date
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("produceDate");
        EasyExcel.write(out, FruitExportDto.class)
                .excludeColumnFiledNames(excludeColumnFiledNames)
                .registerWriteHandler(EasyExcelUtil.getCommonExcelStyle())
                .registerWriteHandler(new ColumnWidthStyleStrategy())
                .autoCloseStream(true)
                .sheet("sheet")
                .doWrite(exportList);
        out.flush();
    }


}
