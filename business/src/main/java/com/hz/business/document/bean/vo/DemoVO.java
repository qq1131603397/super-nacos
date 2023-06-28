package com.hz.business.document.bean.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author： pt
 * @date： 2023/6/28 9:13
 * @discription
 */
@Data
public class DemoVO {

    @ExcelProperty("脚本名称")
    //@ExcelProperty(index = 0)
    //@ExcelProperty({"表头一", "脚本名称"})
    private String screenplayName;

    @ExcelProperty("协议名称")
    private String protocolName;

    @ExcelProperty("扫描端口")
    private String scanPort;

    @ExcelProperty("扫描类型")
    private String scanType;

    @ExcelProperty("扫描等级")
    private String scanLevel;

    @ExcelProperty("脚本描述")
    private String screenplayDes;

    @ExcelProperty("协议类别")
    private String protocolType;

}
