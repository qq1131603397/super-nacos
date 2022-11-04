package com.hz.bussiness.document.service;

import com.alibaba.excel.util.CollectionUtils;
import com.google.common.collect.Lists;
import com.hz.bussiness.common.utils.TimeUtils;
import com.hz.bussiness.document.bean.dto.FruitExportDto;
import com.hz.bussiness.document.entity.Fruit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author： pt
 * @date： 2022/9/1 14:08
 * @discription
 */
@Service
public class EasyExcelService {

    @Resource
    private FruitService fruitService;

    // 初始化数据
    public List<FruitExportDto> getFruitData() {
        List<FruitExportDto> fruitExportDtos = Lists.newArrayList();
        List<Fruit> fruits = fruitService.list();
        for (Fruit fruit : fruits) {
            FruitExportDto fruitExportDto = new FruitExportDto();
            BeanUtils.copyProperties(fruit, fruitExportDto);
            fruitExportDto.setProduceDate(TimeUtils.format(fruit.getProduceDate(), TimeUtils.DEFAULT_TPL));
            fruitExportDtos.add(fruitExportDto);
        }
        return fruitExportDtos;
    }

    // 获取一列元素的合并配置集合
    public List<Integer> getGroupData(List<String> exportDataList) {
        if (CollectionUtils.isEmpty(exportDataList)) {
            return Lists.newArrayList();
        }

        List<Integer> groupCountList = Lists.newArrayList();
        int count = 1;

        for (int i = 1; i < exportDataList.size(); i++) {
            if (exportDataList.get(i).equals(exportDataList.get(i - 1))) {
                count++;
            } else {
                groupCountList.add(count);
                count = 1;
            }
        }

        // 处理完最后一条后
        groupCountList.add(count);
        return groupCountList;
    }

    public Map<String, List<Integer>> getMergeMap() {
        Map<String, List<Integer>> mergeMap = new HashMap<>();
        mergeMap.put("0", getGroupData(getFruitData().stream().map(FruitExportDto::getCategory).collect(Collectors.toList())));
        mergeMap.put("1", getGroupData(getFruitData().stream().map(FruitExportDto::getFruit).collect(Collectors.toList())));
        mergeMap.put("2", getGroupData(getFruitData().stream().map(FruitExportDto::getColor).collect(Collectors.toList())));
        return mergeMap;
    }
}
