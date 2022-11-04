package com.hz.es.service;

import com.hz.es.entity.Student;
import com.hz.es.entity.Teacher;
import com.hz.es.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author： pt
 * @date： 2022/10/8 15:56
 * @discription
 */
@Service
public class TeacherService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TeacherRepository teacherRepository;

    public void initData() {
        Student zs = new Student();
        zs.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        zs.setName("张三");
        zs.setAge(18);
        zs.setMobile("18822334455");
        Student ls = new Student();
        ls.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        ls.setName("李四");
        ls.setAge(20);
        ls.setMobile("18811223344");
        Student ww = new Student();
        ww.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        ww.setName("王五");
        ww.setAge(22);
        ww.setMobile("18833445566");
        List<Student> mathList = new ArrayList();
        List<Student> englishList = new ArrayList();
        mathList.add(zs);
        mathList.add(ls);
        englishList.add(ls);
        englishList.add(ww);

        Teacher math = new Teacher();
        math.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        math.setName("数学老师");
        math.setMobile("18855667788");
        math.setPassword("123456");
        math.setSubject("数学");
        math.setStudents(mathList);
        teacherRepository.save(math);

        Teacher english = new Teacher();
        english.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        english.setName("英语老师");
        english.setMobile("18866778899");
        english.setPassword("123456");
        english.setSubject("英语");
        english.setStudents(englishList);
        teacherRepository.save(english);
    }

}
