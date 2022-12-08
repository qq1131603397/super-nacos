package com.hz.business.document.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.business.document.entity.Fruit;
import com.hz.business.document.mapper.FruitMapper;
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

    public List<Fruit> queryList() {
        String fruit = "青色";
        return fruitMapper.findAllByFruit(fruit);
    }
}
