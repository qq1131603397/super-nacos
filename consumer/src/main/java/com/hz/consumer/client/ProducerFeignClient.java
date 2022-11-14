package com.hz.consumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author： pt
 * @date： 2022/11/14 13:23
 * @discription
 */
@FeignClient(value = "producer")
public interface ProducerFeignClient {

    @GetMapping(value = "/producer/feign")
    public String testFeign(String name);

}
