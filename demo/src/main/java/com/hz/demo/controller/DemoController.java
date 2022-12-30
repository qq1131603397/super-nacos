package com.hz.demo.controller;

import com.hz.demo.anno.MyAnno;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： pt
 * @date： 2022/12/1 18:02
 * @discription
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping("test")
    @MyAnno()
    public void openApi(){
        System.out.println(111);
    }

}
