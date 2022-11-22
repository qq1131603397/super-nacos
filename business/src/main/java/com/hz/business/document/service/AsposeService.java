package com.hz.business.document.service;

import com.alibaba.fastjson.JSONObject;
import com.aspose.words.*;
import com.aspose.words.net.System.Data.DataSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hz.business.document.bean.vo.FileTransferVo;
import com.hz.business.document.util.AsposeWordsHelper;
import com.hz.common.utils.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

/**
 * @author： pt
 * @date： 2022/11/21 16:14
 * @discription
 */
@Service
public class AsposeService {

    private final List<String> warnInfosHeader = Lists.newArrayList("告警事件类型", "告警内容", "处置结果", "时间");
    private final List<String> fileInfosHeader = Lists.newArrayList("文件名称", "传输方式", "大小", "传输方向", "时间");
    private final List<String> opsDetailsHeader = Lists.newArrayList("操作内容", "时间");
    private final List<Double> headerWidths = Lists.newArrayList(125.0, 200.0, 125.0, 200.0);
    private final List<Double> warnInfosWidths = Lists.newArrayList(180.0, 250.0, 200.0, 120.0);
    private final List<Double> fileInfosWidths = Lists.newArrayList(250.0, 120.0, 100.0, 100.0, 180.0);
    private final List<Double> opsDetailsWidths = Lists.newArrayList(325.0, 325.0);

    /**
     * 导出文档
     *
     * @param fileName
     * @param response
     */
    public void exportAspose(String fileName, HttpServletResponse response) {
        try (FileInputStream in = new FileInputStream(fileName)) {
            response.setContentType("application/pdf");
            OutputStream outputStream = response.getOutputStream();
            int count;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = in.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成aspose文档
     *
     * @param filePath
     * @param response
     * @throws Exception
     */
    public void handleAsposeReport(String filePath, HttpServletResponse response) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("tpl/aspose.docx");
        File file = classPathResource.getFile();
        Document document = new Document(new FileInputStream(file));
        //行式结构数据填充
        Map<String, Object> map = new HashMap<>();
        generalTimeInfo(map);
        generalTaskInfo(map);
        AsposeWordsHelper.mergeDocumentProperties(document, map);
        //列式数据填充
        DataSet dataSet = new DataSet();
        List<JSONObject> fileTransferObjects = generalFileTransferData();
        Set<String> fileSet = Sets.newHashSet("fileName", "fileType", "fileSize",
                "operateType", "operateDirect", "createTime");
        dataSet.getTables().add(AsposeWordsHelper.generalDataTable(fileSet, fileTransferObjects, "fileTransferTable"));
        document.getMailMerge().executeWithRegions(dataSet);
        //移动到书签位置
        DocumentBuilder builder = new DocumentBuilder(document);
        builder.moveToBookmark("mark_opsDetails");
        //自定义表格
        buildData(builder);
        document.save(filePath + ".pdf", SaveFormat.PDF);
        exportAspose(filePath, response);
    }

    /**
     * 生成表格数据
     *
     * @param builder
     * @throws Exception
     */
    private void buildData(DocumentBuilder builder) throws Exception {
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        builder.getFont().setSize(10.5);
        builder.getFont().setName("宋体");
        AsposeWordsHelper.buildHeader(builder, headerWidths);
        if (getWarnInfos().size() > 0) {
            AsposeWordsHelper.buildTitle(builder, "告警信息", 4, warnInfosWidths);
            AsposeWordsHelper.buildBody(builder, warnInfosHeader, getWarnInfos(), warnInfosWidths);
        }
        if (getFileInfos().size() > 0) {
            AsposeWordsHelper.buildTitle(builder, "文件传输", 5, fileInfosWidths);
            AsposeWordsHelper.buildBody(builder, fileInfosHeader, getFileInfos(), fileInfosWidths);
        }
        if (getOpsDetails().size() > 0) {
            AsposeWordsHelper.buildTitle(builder, "运维内容", 2, opsDetailsWidths);
            AsposeWordsHelper.buildBody(builder, opsDetailsHeader, getOpsDetails(), opsDetailsWidths);
        }
    }

    /**
     * 运维内容
     *
     * @return
     */
    private List<List<Object>> getOpsDetails() {
        List<List<Object>> opsDetailsList = Lists.newArrayList();
        opsDetailsList.add(Arrays.asList("文件拷出", "2022-11-18 09:33:51"));
        opsDetailsList.add(Arrays.asList("文件拷出", "2022-11-18 09:47:52"));
        return opsDetailsList;
    }

    /**
     * 文件传输
     *
     * @return
     */
    private List<List<Object>> getFileInfos() {
        List<List<Object>> fileInfosList = Lists.newArrayList();
        fileInfosList.add(Arrays.asList("aspose.txt", "USB", "22.37KB", "下行", "2022-11-18 09:33:51"));
        fileInfosList.add(Arrays.asList("aspose.pdf", "USB", "9.0KB", "下行", "2022-11-18 09:47:52"));
        return fileInfosList;
    }

    /**
     * 告警信息
     *
     * @return
     */
    private List<List<Object>> getWarnInfos() {
        List<List<Object>> warnInfosList = Lists.newArrayList();
        warnInfosList.add(Arrays.asList("违规外联", "外设连接", "超时处置：禁止", "2022-11-18 09:33:51"));
        warnInfosList.add(Arrays.asList("违规外联", "违规外联", "二次授权：禁止", "2022-11-18 09:28:34"));
        return warnInfosList;
    }

    /**
     * 生成文件传输数据
     *
     * @return
     */
    private List<JSONObject> generalFileTransferData() {
        List<JSONObject> list = Lists.newArrayList();
        list.add(JSONObject.parseObject(new FileTransferVo("aspose.txt", "txt", "22.37KB", "USB", "下行", "2022-11-18 09:47:54").toString()));
        list.add(JSONObject.parseObject(new FileTransferVo("fileTransfer.pdf", "pdf", "82.91KB", "USB", "下行", "2022-11-18 09:45:52").toString()));
        list.add(JSONObject.parseObject(new FileTransferVo("fileTransfer.docx", "docx", "20.58KB", "USB", "下行", "2022-11-18 09:43:16").toString()));
        return list;
    }

    /**
     * 生成任务信息
     *
     * @param map
     */
    private void generalTaskInfo(Map<String, Object> map) {
        map.put("task_name", "aspose任务");
        map.put("work_order_no", "123456");
        map.put("applicationUnit", "xx工作单位");
        map.put("station_name", "xx厂站");
    }

    /**
     * 生成时间信息
     *
     * @param map
     */
    private void generalTimeInfo(Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        map.put("year", calendar.get(Calendar.YEAR));
        map.put("month", calendar.get(Calendar.MONTH) + 1);
        map.put("date", calendar.get(Calendar.DATE));
    }

}
