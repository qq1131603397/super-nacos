package com.hz.producer.produce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： pt
 * @date： 2022/11/14 13:17
 * @discription
 */
@RestController
@RequestMapping("producer")
public class ProducerController {

    @GetMapping(value = "feign", produces = "application/json; charset=utf-8")
    public String testFeign(@RequestParam("name") String name) {
        return name + "调用了Producer服务";
    }

}
