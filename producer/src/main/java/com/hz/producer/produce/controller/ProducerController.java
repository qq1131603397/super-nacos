package com.hz.producer.produce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author： pt
 * @date： 2022/11/14 13:17
 * @discription
 */
@RequestMapping("producer")
public class ProducerController {

    @GetMapping("feign")
    public String testFeign(String name) {
        return name + "调用了Producer服务";
    }

}
