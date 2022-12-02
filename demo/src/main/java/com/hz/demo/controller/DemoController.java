package com.hz.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.hz.demo.util.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author： pt
 * @date： 2022/12/1 18:02
 * @discription
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("openApi")
    public void openApi(){
        String showDocApiUrl = "https://www.showdoc.cc/server/api/item/updateByApi";
        JSONObject parMap = new JSONObject();
        parMap.put("api_key", "bb9722e1ea4bc2ec38257ad1e81284541968011357");
        parMap.put("api_token", "f6a6020a354d6829b7b5b31f7e82c00f2099062845");
        parMap.put("cat_name", "管理员/用户登录");
        parMap.put("page_title", "API测试");
        parMap.put("page_content", "### 库表操作SQL\n" +
                "\n" +
                "#### 一、查库表名是否存在\n" +
                "\n" +
                "```sql\n" +
                "select TABLE_NAME as tableName from dba_tables where owner=#{ower}  and TABLE_NAME like CONCAT('%',#{tableName},'%');\n" +
                "```\n" +
                "\n" +
                "#### 二、查数据表字段是否存在\n" +
                "\n" +
                "```sql\n" +
                "select count(*) from dba_tab_cols where owner = 'EM' and TABLE_NAME = 'EM_LINE' and COLUMN_NAME = 'ID'\n" +
                "```\n" +
                "\n" +
                "#### 三、获取表的结构\n" +
                "\n" +
                "```sql\n" +
                "select \"club_order_refound\" as 名称, \"NO\" as 是否允许为空, \"表\" 类型, \"table\" as 类型描述, (\n" +
                "              SELECT TABLE_COMMENT FROM information_schema.TABLES WHERE table_schema='club' and TABLE_NAME = 'club_order_refound'\n" +
                "        ) as 描述 from dual \n" +
                "union all\n" +
                "select column_name, is_nullable, \"字段\" 类型, column_type, column_comment\n" +
                "from information_schema.columns\n" +
                "where table_schema = 'club' and table_name = 'club_order_refound'\n" +
                "union all\n" +
                "select \"\", \"\", \"\", \"\", \"\" from dual\n" +
                "union all\n" +
                "select \"\", \"\", \"\", \"\", \"\" from dual;\n" +
                "```\n" +
                "\n" +
                "#### 四、导入导出数据\n" +
                "\n" +
                "```shell\n" +
                "# 导入数据\n" +
                "mysql -u root -p weilong < weilong_test.sql\n" +
                "\n" +
                "# 导出数据\n" +
                "mysqldump -u root -p weilong > weilong.sql\n" +
                "```");
        HttpUtil.doPost(showDocApiUrl, parMap.toJSONString());
    }

}
