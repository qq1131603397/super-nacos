package com.hz.es.service;

import com.hz.es.entity.Student;
import com.hz.es.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author： pt
 * @date： 2022/10/8 15:56
 * @discription
 */
@Service
public class StudentService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private StudentRepository studentRepository;

    public void initData() {
        Student zs = new Student();
        zs.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        zs.setName("张三");
        zs.setAge(18);
        zs.setMobile("18822334455");
        studentRepository.save(zs);
        Student ls = new Student();
        ls.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        ls.setName("李四");
        ls.setAge(20);
        ls.setMobile("18811223344");
        studentRepository.save(ls);
        Student ww = new Student();
        ww.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        ww.setName("王五");
        ww.setAge(22);
        ww.setMobile("18833445566");
        studentRepository.save(ww);
    }

}
