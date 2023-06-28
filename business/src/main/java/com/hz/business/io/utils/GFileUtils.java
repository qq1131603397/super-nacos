package com.hz.business.io.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * @author： pt
 * @date： 2023/6/28 9:53
 * @discription
 */
public class GFileUtils {

    protected static final Logger logger = LoggerFactory.getLogger(GFileUtils.class);

    public GFileUtils() {
    }

    /**
     * 获取文件夹所占大小
     *
     * @param dirPath 文件路径
     * @return 文件大小
     */
    public static long getFileSize(String dirPath) {
        File dir = new File(dirPath);
        long totalSize = 0L;
        if (dir.exists()) {
            if (dir.isFile()) {
                totalSize = FileUtils.sizeOf(dir);
            } else {
                Collection<File> files = FileUtils.listFiles(dir, null, true);
                File file;
                for (Iterator<File> var5 = files.iterator(); var5.hasNext(); totalSize += FileUtils.sizeOf(file)) {
                    file = var5.next();
                }
            }
        }
        return totalSize;
    }

    /**
     * 统计多文件大小
     *
     * @param files 文件群
     * @return 文件总计大小
     */
    public static long getMultiFileSize(List<String> files) {
        long totalSize = 0L;
        String fileName;
        for (Iterator<String> iterator = files.iterator(); iterator.hasNext(); totalSize += getFileSize(fileName)) {
            fileName = iterator.next();
        }
        return totalSize;
    }

    /**
     * 获取文件夹文件列表
     *
     * @param dirPath    文件夹路径
     * @param extensions 文件后缀
     * @param recursive  是否递归
     * @return 文件列表
     */
    public static Collection<File> listFiles(String dirPath, String[] extensions, boolean recursive) {
        File file = new File(dirPath);
        return FileUtils.listFiles(file, extensions, recursive);
    }

    /**
     * 获取文件名
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    public static String getFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }

    /**
     * 获取父级文件夹路径
     *
     * @param filePath 文件路径
     * @return 父级文件夹绝对路径
     */
    public static String getBasePath(String filePath) {
        File file = new File(filePath);
        return file.getParent();
    }

    /**
     * 遍历获取文件夹下所有文件
     *
     * @param dir 文件路径
     * @return 文件列表
     */
    public static List<String> getFiles(String dir) {
        List<String> lstFiles = Lists.newArrayList();
        File die = new File(dir);
        for (File file : Objects.requireNonNull(die.listFiles())) {
            if (file.isDirectory()) {
                lstFiles.add(file.getAbsolutePath());
                lstFiles.addAll(getFiles(file.getAbsolutePath()));
            } else {
                String str = file.getAbsolutePath();
                lstFiles.add(str);
            }
        }
        return lstFiles;
    }

    /**
     * 获取文件后缀
     *
     * @param filename 文件路径
     * @return 文件后缀
     */
    public static String getExtensionName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf(46);
            if (dot > -1 && dot < filename.length() - 1) {
                return filename.substring(dot + 1);
            }
        }
        return "";
    }

    /**
     * 获取无后缀文件名称
     *
     * @param filename 文件路径
     * @return 无后缀文件名称
     */
    public static String getFileNameNoEx(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf(46);
            if (dot > -1 && dot < filename.length()) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 拼接文件
     *
     * @param inputStream 输入流
     * @param newFilePath 目的文件
     */
    public static void appendFile(InputStream inputStream, String newFilePath) {
        File newFile = new File(newFilePath);
        FileOutputStream fs = null;
        try {
            if (!newFile.exists()) {
                FileUtils.touch(newFile);
            }
            fs = new FileOutputStream(newFile, true);
            byte[] buffer = new byte[1048576];
            int byteread;
            while ((byteread = inputStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
                fs.flush();
            }
        } catch (Exception e) {
            logger.info("", e);
        } finally {
            IOGUtils.close(fs);
            IOGUtils.close(inputStream);
        }
    }

    /**
     * 创建文件
     *
     * @param file 文件路径
     */
    public static void touch(String file) {
        try {
            FileUtils.touch(new File(file));
        } catch (Exception e) {
            logger.info("", e);
        }
    }

    /**
     * 拼接文件路径
     *
     * @param paths 路径层级
     * @return 文件路径
     */
    public static String joinPath(String... paths) {
        return Joiner.on(File.separator).join(paths);
    }

}
