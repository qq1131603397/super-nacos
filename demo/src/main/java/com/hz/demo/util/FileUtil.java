package com.hz.demo.util;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author： pt
 * @date： 2023/1/9 10:36
 * @discription
 */
public class FileUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static Collection<File> listFilesAndDirs(String dirName, String[] extensions, boolean recursive) {
        File file = new File(dirName);
        IOFileFilter filter;
        if (extensions == null) {
            filter = TrueFileFilter.INSTANCE;
        } else {
            String[] suffixes = toSuffixes(extensions);
            filter = new SuffixFileFilter(suffixes);
        }
        return FileUtils.listFilesAndDirs(file, filter, recursive ? TrueFileFilter.INSTANCE : FalseFileFilter.INSTANCE);
    }

    private static String[] toSuffixes(String[] extensions) {
        String[] suffixes = new String[extensions.length];
        for (int i = 0; i < extensions.length; ++i) {
            suffixes[i] = "." + extensions[i];
        }
        return suffixes;
    }

    public static void listFilesTest(List<File> files, File filePath, String[] params) {
        if (filePath.isDirectory()) {
            File[] tempFiles = filePath.listFiles();
            if (tempFiles != null && tempFiles.length > 0) {
                for (File file : tempFiles) {
                    if (!file.exists()){
                        System.out.println("文件不存在：" + file.getName());
                        continue;
                    }
                    if (isMessyCode(file.getName())) {
                        System.out.println("乱码文件：" + file.getName());
                        logger.info("乱码文件：" + file.getName());
                    } else {
                        if (file.isDirectory()) {
                            listFilesTest(files, file, params);
                        } else if (file.isFile()) {
                            for (String param : params) {
                                if (file.getName().endsWith(".".concat(param))) {
                                    files.add(file);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    private static boolean isMessyCode(String strName) {
        try {
            Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
            Matcher m = p.matcher(strName);
            String after = m.replaceAll("");
            String temp = after.replaceAll("\\p{P}", "");
            char[] ch = temp.trim().toCharArray();
            for (char c : ch) {
                if (!Character.isLetterOrDigit(c)) {
                    String str = "" + c;
                    if (!str.matches("[\u4e00-\u9fa5]+")) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.info("" + e);
        }
        return false;
    }


    public static void main(String[] args) {
        File file = new File("F:\\密盾兼容_运维终端视频记录20230523171114");
        String[] params = new String[]{"xlsx"};
//        Collection<File> files = FileUtils.listFiles(file, params, true);
        List<File> files = Lists.newArrayList();
        listFilesTest(files, file, params);
        System.out.println(files.size());
    }
}
