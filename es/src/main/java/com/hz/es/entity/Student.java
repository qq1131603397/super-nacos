package com.hz.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author： pt
 * @date： 2022/10/8 15:44
 * @discription
 */
@Data
@Document(indexName = "student", type = "doc")
public class Student {

    /**
     * 数据id Id注解：指明pms索引_doc类型中的文档的id为数据的id。如果insert到es中的数据的id值为空，那么es会自动生成一个唯一的uuid作为文档id
     */
    @Id
    private String id;

    /**
     * 姓名 Text：其取代了string，当一个字段是要被全文搜索的，比如Email内容、产品描述，应该使用text类型。 设置text类型以后，字段内容会被分词，在生成倒排索引以前，字符串会被分词器分成一个个词项。text类型的字段不用于排序，很少用于聚合（termsAggregation除外）。
     * ik_max_word：指示了该字段使用到的分词算法，为：最细粒度分词
     */
    @Field(type = FieldType.Text)
    private String name;

    /**
     * 手机号 Keyword：关键词。该类型字段只能通过"精确查找"方式索引到，不会对该字段分词后与查询条件匹配
     */
    @Field(type = FieldType.Keyword)
    private String mobile;

    /**
     * 年龄 Integer：整数类型
     */
    @Field(type = FieldType.Integer)
    private Integer age;

}
