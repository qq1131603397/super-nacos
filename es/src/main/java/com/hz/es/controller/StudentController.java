package com.hz.es.controller;

import com.hz.es.entity.ResultVO;
import com.hz.es.entity.Student;
import com.hz.es.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author： pt
 * @date： 2022/11/29 10:12
 * @discription
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("init")
    public ResultVO initData() {
        try {
            studentService.initData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.failure("初始化es数据失败");
        }
        return ResultVO.success("初始化es数据成功");
    }

    @PostMapping("saveData")
    public ResultVO saveData(@RequestBody Student student) {
        try {
            studentService.saveData(student);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.failure("保存数据失败");
        }
        return ResultVO.success("保存数据成功");
    }


}
