package com.hz.bussiness.document.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.bussiness.document.entity.Fruit;
import com.hz.bussiness.document.mapper.FruitMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author： pt
 * @date： 2022/10/13 16:03
 * @discription
 */
@Service
public class FruitService {

    @Resource
    private FruitMapper fruitMapper;

    public void insert(Fruit fruit) {
        fruit.setProduceDate(System.currentTimeMillis());
        fruitMapper.insert(fruit);
    }

    public List<Fruit> list() {
        QueryWrapper<Fruit> queryWrapper = new QueryWrapper<>();
        return fruitMapper.selectList(queryWrapper);
    }
}
