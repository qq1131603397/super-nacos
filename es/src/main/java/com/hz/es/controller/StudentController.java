package com.hz.es.controller;

import com.hz.common.entity.ResultVO;
import com.hz.es.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： pt
 * @date： 2022/10/8 15:53
 * @discription
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("init")
    public ResultVO initData(){
        studentService.initData();
        return new ResultVO();
    }

}
