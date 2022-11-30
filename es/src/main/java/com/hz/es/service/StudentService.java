package com.hz.es.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.es.entity.Student;
import com.hz.es.mapper.StudentMapper;
import com.hz.es.repository.StudentRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author： pt
 * @date： 2022/11/29 10:14
 * @discription
 */
@Service
public class StudentService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Resource
    private StudentMapper studentMapper;

    public Student save(Student bean) {
        return studentRepository.save(bean);
    }

    public Iterable<Student> saveAll(List<Student> list) {
        return studentRepository.saveAll(list);
    }

    @Transactional(rollbackFor = Exception.class)
    public void initData() {
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        List<Student> students = studentMapper.selectList(lqw);
        saveAll(students);
    }

    public void saveData(Student student) {
        save(student);
    }

    /**
     * 模糊查询
     *
     * @return
     */
    public List<Student> matchQuery() {
//        PageRequest page = PageRequest.of(0, 30, Sort.by(Sort.Direction.DESC, "name"));
        PageRequest page = PageRequest.of(0, 30);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", "张三"))
                .withPageable(page)
//                .withSort(SortBuilders.fieldSort("name").order(SortOrder.DESC))
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

    /**
     * 全等查询
     *
     * @return
     */
    public List<Student> termQuery() {
        Pageable pageable = PageRequest.of(0, 10);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("name", "张三"))
                .withPageable(pageable)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

    /**
     * 根据单个id查询
     *
     * @return
     */
    public Student getQuery(String id) {
        GetQuery getQuery = GetQuery.getById(id);
        return elasticsearchTemplate.queryForObject(getQuery, Student.class);
    }

    /**
     * 范围查询
     *
     * @return
     */
    public List<Student> rangeQuery() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.rangeQuery("age").from(10).to(18))
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

    /**
     * 组合查询
     *
     * @return
     */
    public List<Student> boolQuery() {
        QueryBuilder filterQuery = QueryBuilders
                .boolQuery()
                .filter(QueryBuilders.termQuery("name", "张三"))
                .filter(QueryBuilders.termQuery("class", "Grade 7"));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(filterQuery)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

}
