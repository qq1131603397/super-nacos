package com.hz.es.repository;

import com.hz.es.entity.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author： pt
 * @date： 2022/11/29 10:14
 * @discription
 */
public interface StudentRepository extends ElasticsearchRepository<Student, String> {
}
