package com.hz.es.repository;

import com.hz.es.entity.Teacher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author： pt
 * @date： 2022/10/8 15:56
 * @discription
 */
public interface TeacherRepository extends ElasticsearchRepository<Teacher, String> {
}
