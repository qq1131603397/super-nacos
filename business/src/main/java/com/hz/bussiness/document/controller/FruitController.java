package com.hz.bussiness.document.controller;

import com.hz.bussiness.document.entity.Fruit;
import com.hz.bussiness.document.service.FruitService;
import com.hz.common.entity.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author： pt
 * @date： 2022/10/13 16:02
 * @discription
 */
@RestController
@RequestMapping("fruit")
public class FruitController {

    private final static Logger logger = LoggerFactory.getLogger(FruitController.class);

    @Autowired
    private FruitService fruitService;

    @PostMapping("insert")
    public ResultVO insert(@RequestBody Fruit fruit) {
        try {
            fruitService.insert(fruit);
        } catch (Exception e) {
            logger.error("", e);
            return ResultVO.failure("新增失败");
        }
        return ResultVO.success("新增成功");
    }

}
