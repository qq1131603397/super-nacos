package com.hz.es.controller;

import com.hz.common.entity.ResultVO;
import com.hz.es.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： pt
 * @date： 2022/10/8 15:53
 * @discription
 */
@RestController
@RequestMapping("teacher")
@EnableScheduling
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("init")
    public ResultVO initData() {
        teacherService.initData();
        return new ResultVO();
    }

}
