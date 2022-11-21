package com.hz.common.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author： pt
 * @date： 2022/11/21 16:24
 * @discription
 */
public class FileUtils {

    public static File getFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            file = ResourceUtils.getFile("classpath:" + filePath);
        }

        return file;
    }

}
