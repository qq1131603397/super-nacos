package com.hz.business.document.bean.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author： pt
 * @date： 2022/11/21 16:45
 * @discription
 */
@Data
@AllArgsConstructor
public class FileTransferVo {

    private String fileName;

    private String fileType;

    private String fileSize;

    private String operateType;

    private String operateDirect;

    private String createTime;

    @Override
    public String toString() {
        return JSONObject.toJSONString(new FileTransferVo(fileName, fileType, fileSize, operateType, operateDirect, createTime));
    }

}
