package com.hz.knife.fallback;

import com.hz.knife.client.ProducerFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author： pt
 * @date： 2022/11/16 13:28
 * @discription
 */
@Component
public class ProducerClientFallback implements ProducerFeignClient {

    @Override
    public String testFeign(String name) {
        return null;
    }

    @Override
    public String testHystrix() {
        return "服务器繁忙，请稍后再试！";
    }

}
