package com.hz.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author： pt
 * @date： 2022/11/29 10:04
 * @discription
 */
@Data
@Document(indexName = "student", type = "doc")
public class Student {

    @Id
    private String id;

    /**
     * 姓名 Text：其取代了string，当一个字段是要被全文搜索的，比如Email内容、产品描述，应该使用text类型。
     * 设置text类型以后，字段内容会被分词，在生成倒排索引以前，字符串会被分词器分成一个个词项。
     * text类型的字段不用于排序，很少用于聚合（termsAggregation除外）。
     */
    @Field(type = FieldType.Text)
    private String name;

    /**
     * 年龄 Integer：整数类型
     */
    @Field(type = FieldType.Integer)
    private String age;

    /**
     * Keyword：关键词。该类型字段只能通过"精确查找"方式索引到，不会对该字段分词后与查询条件匹配
     */
    @Field(type = FieldType.Keyword)
    private String grade;

}
