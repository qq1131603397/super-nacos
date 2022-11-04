package com.hz.bussiness.document.controller;

import com.alibaba.excel.EasyExcel;
import com.hz.bussiness.document.service.EasyExcelService;
import com.hz.bussiness.document.util.ColumnWidthStyleStrategy;
import com.hz.bussiness.document.util.EasyExcelUtil;
import com.hz.bussiness.document.util.MergeStrategy;
import com.hz.bussiness.document.bean.dto.FruitExportDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
