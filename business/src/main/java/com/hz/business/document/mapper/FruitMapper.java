package com.hz.business.document.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hz.business.document.entity.Fruit;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author： pt
 * @date： 2022/10/13 15:59
 * @discription
 */
public interface FruitMapper extends BaseMapper<Fruit> {

    @Select("SELECT * FROM fruit where color = #{color}")
    List<Fruit> findAllByFruit(String color);

    List<Fruit> findAllByFruitXml(String name);

}
