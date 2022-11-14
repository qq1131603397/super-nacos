package com.hz.consumer.consume.controller;

import com.hz.consumer.client.ProducerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： pt
 * @date： 2022/11/14 11:11
 * @discription
 */
@RestController
@RequestMapping("customer")
@RefreshScope
public class CustomerController {

    @Value("${custom.name}")
    private String customName;

    @Autowired
    private ProducerFeignClient producerFeignClient;

    @GetMapping("getName")
    public String getName(){
        return customName;
    }

    @GetMapping("testFeign")
    public String testFeign(){
        return producerFeignClient.testFeign("consumer");
    }

}