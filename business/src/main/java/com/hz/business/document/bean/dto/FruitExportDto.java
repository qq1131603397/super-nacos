package com.hz.business.document.bean.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author： pt
 * @date： 2022/9/1 14:10
 * @discription
 */
@Data
public class FruitExportDto {

//    @ExcelProperty(value = { "物品种类", "物品种类"}, index = 0)
    @ExcelProperty(value = "物品种类", index = 0)
    private String category;

    @ExcelProperty(value = "水果名称", index = 1)
    private String fruit;

    private String produceDate;

    @ExcelProperty(value = "水果颜色", index = 2)
    private String color;


}
