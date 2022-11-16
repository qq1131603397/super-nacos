package com.hz.consumer.client;

import com.hz.consumer.fallback.ProducerClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author： pt
 * @date： 2022/11/14 13:23
 * @discription
 */
@FeignClient(value = "producer", fallback = ProducerClientFallback.class)
public interface ProducerFeignClient {

    @RequestMapping(value = "/producer/feign", method = RequestMethod.GET)
    String testFeign(@RequestParam("name") String name);

    @RequestMapping(value = "/producer/hystrix", method = RequestMethod.GET)
    String testHystrix();

}
