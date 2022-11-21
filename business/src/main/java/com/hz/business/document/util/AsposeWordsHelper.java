package com.hz.business.document.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.CellMerge;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.net.System.Data.DataRow;
import com.aspose.words.net.System.Data.DataTable;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author： pt
 * @date： 2022/11/21 17:06
 * @discription
 */
public class AsposeWordsHelper {

    /**
     * 构建公用的运维信息
     *
     * @param builder
     * @throws Exception
     */
    public static void buildHeader(DocumentBuilder builder, List<Double> widths) throws Exception {
        builder.startTable();
        builder.insertCell();
        builder.getCellFormat().getShading().clearFormatting();
        builder.getCellFormat().setWidth(widths.get(0));
        builder.write("设备名称");
        builder.insertCell();
        builder.getCellFormat().setWidth(widths.get(1));
        builder.write("aspose");
        builder.insertCell();
        builder.getCellFormat().setWidth(widths.get(2));
        builder.write("运维模式");
        builder.insertCell();
        builder.getCellFormat().setWidth(widths.get(3));
        builder.write("网口模式");
        builder.endRow();
        builder.endTable();
    }

    /**
     * 构建合并后的标题信息
     *
     * @param builder
     * @param titleName
     * @param size
     * @throws Exception
     */
    public static void buildTitle(DocumentBuilder builder, String titleName, int size, List<Double> widths) throws Exception {
        builder.startTable();
        builder.insertCell();
        builder.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
        builder.getCellFormat().getShading().setBackgroundPatternColor(new Color(192, 192, 192));
        builder.write(titleName);
        for (int i = 0; i < size - 1; i++) {
            builder.insertCell();
            builder.getCellFormat().setWidth(widths.get(i));
            builder.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
        }
        builder.endRow();
    }

    /**
     * 构建表格body内容
     *
     * @param builder
     * @param headers
     * @param contentsList
     * @throws Exception
     */
    public static void buildBody(DocumentBuilder builder, List<String> headers, List<List<Object>> contentsList, List<Double> widths) throws Exception {
        builder.getCellFormat().setHorizontalMerge(CellMerge.NONE);
        builder.getCellFormat().getShading().clearFormatting();
        for (int i = 0; i < headers.size(); i++) {
            builder.insertCell();
            builder.getCellFormat().setWidth(widths.get(i));
            builder.write(headers.get(i));
        }
        builder.endRow();
        for (List<Object> contents : contentsList) {
            for (int i = 0; i < contents.size(); i++) {
                builder.insertCell();
                builder.getCellFormat().setWidth(widths.get(i));
                builder.write(String.valueOf(contents.get(i)));
            }
            builder.endRow();
        }
        builder.endTable();
    }

    /**
     * 行式数据填充
     *
     * @param document
     * @param map
     * @throws Exception
     */
    public static void mergeDocumentProperties(Document document, Map<String, Object> map) throws Exception {
        String[] keys = map.keySet().toArray(new String[0]);
        Object[] values = map.values().toArray();
        document.getMailMerge().execute(keys, values);
    }

    /**
     * 列式数据填充
     *
     * @param properties
     * @param tableList
     * @param tableName
     * @return
     */
    public static DataTable generalDataTable(Set<String> properties, List<JSONObject> tableList, String tableName) {
        DataTable dt = new DataTable(tableName);
        properties.forEach(param -> dt.getColumns().add(param));
        if (tableList.size() > 0) {
            tableList.forEach(AsposeWordsHelper::filterNull);
            for (JSONObject jsonObject : tableList) {
                DataRow row = dt.newRow();
                for (String key : jsonObject.keySet()) {
                    row.set(key, jsonObject.get(key));
                }
                dt.getRows().add(row);
            }
        } else {
            DataRow row = dt.newRow();
            for (int i = 0; i < dt.getColumns().getCount(); i++) {
                row.set(i, "");
            }
            dt.getRows().add(row);
        }
        return dt;
    }

    /**
     * 替换null值为 “”
     *
     * @param jsonObj
     * @return
     */
    public static JSONObject filterNull(JSONObject jsonObj) {
        Iterator<String> it = jsonObj.keySet().iterator();
        Object obj = null;
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            obj = jsonObj.get(key);
            if (obj instanceof JSONObject) {
                filterNull((JSONObject) obj);
            }
            if (obj instanceof JSONArray) {
                JSONArray objArr = (JSONArray) obj;
                for (int i = 0; i < objArr.size(); i++) {
                    filterNull(objArr.getJSONObject(i));
                }
            }
            if (obj == null) {
                jsonObj.put(key, "");
            }
        }
        return jsonObj;
    }
}

