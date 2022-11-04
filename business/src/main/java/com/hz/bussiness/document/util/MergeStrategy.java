package com.hz.bussiness.document.util;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.util.Map;

/**
 * @author： pt
 * @date： 2022/9/1 14:41
 * @discription
 */
public class MergeStrategy<T> extends AbstractMergeStrategy {

    private Map<String, List<Integer>> mergeConfig;
    private Sheet sheet;
    private int startRow;

    public MergeStrategy(int startRow, Map<String, List<Integer>> mergeConfig) {
        this.startRow = startRow;
        this.mergeConfig = mergeConfig;
    }

    /**
     * 按照分组将各种类别分别合并成一个单元格
     *
     * @param index
     */
    private void mergeGroupColumn(String index) {
        int rowCnt = this.startRow;
        for (Integer count : mergeConfig.get(index)) {
            if (count > 1) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(rowCnt, rowCnt + count - 1, Integer.parseInt(index), Integer.parseInt(index));
                sheet.addMergedRegionUnsafe(cellRangeAddress);
            }
            rowCnt += count;
        }
    }

    @Override
    protected void merge(Sheet sheet, Cell cell, Head head, Integer integer) {
        this.sheet = sheet;
        if (cell.getRowIndex() == this.startRow) {
            mergeConfig.forEach((key, value) -> {
                mergeGroupColumn(key);
            });
        }
    }
}
