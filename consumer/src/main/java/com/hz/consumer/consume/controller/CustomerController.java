package com.hz.consumer.consume.controller;

import com.hz.consumer.client.ProducerFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author： pt
 * @date： 2022/11/14 11:11
 * @discription
 */
@RestController
@RequestMapping("consumer")
@RefreshScope
public class CustomerController {

    @Value("${custom.name}")
    private String customName;

    @Resource
    private ProducerFeignClient producerFeignClient;

    @GetMapping(value = "getName", produces = "application/json; charset=utf-8")
    public String getName() {
        return customName;
    }

    @GetMapping(value = "testFeign", produces = "application/json; charset=utf-8")
    public String testFeign() {
        return producerFeignClient.testFeign("consumer");
    }

    @GetMapping(value = "testHystrix", produces = "application/json; charset=utf-8")
    public String testHystrix() {
        return producerFeignClient.testHystrix();
    }

}
