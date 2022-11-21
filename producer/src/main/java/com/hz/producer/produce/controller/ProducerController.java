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
public class ProducerController {

    @GetMapping(value = "feign", produces = "application/json; charset=utf-8")
    public String testFeign(@RequestParam("name") String name) {
        return name + "调用了Producer服务";
    }

    @GetMapping(value = "hystrix", produces = "application/json; charset=utf-8")
    public String testHystrix() {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "调用了Hystrix方法";
    }

}
