package com.hz.business.io.utils;

import com.google.common.collect.Lists;
import com.hz.business.io.bean.PathZipBean;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

/**
 * @author： pt
 * @date： 2023/6/28 9:45
 * @discription
 */
public class ZipUtil {

    private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * 压缩文件
     *
     * @param pathBeans 文件群
     * @param zippath   zip文件路径
     */
    public static void zip(List<PathZipBean> pathBeans, String zippath) {
        ZipArchiveOutputStream zaos = null;
        try {
            File zipFile = new File(zippath);
            zaos = new ZipArchiveOutputStream(zipFile);
            zaos.setUseZip64(Zip64Mode.AsNeeded);
            for (PathZipBean bean : pathBeans) {
                logger.info(MessageFormat.format("开始压缩文件：{0}", bean.getSource()));
                compressFilesZip(zaos, bean);
            }
            zaos.finish();
        } catch (Exception e) {
            logger.info("", e);
        } finally {
            IOGUtils.close(zaos);
        }
    }

    /**
     * 使用apache
     *
     * @param zaos
     * @param pathBean
     */
    public static void compressFilesZip(ZipArchiveOutputStream zaos, PathZipBean pathBean) {
        File aFile = new File(pathBean.getSource());
        System.out.println(aFile.getName());
        List<String> paths = Lists.newArrayList();
        boolean fileFlag = false;
        if (aFile.isDirectory()) {
            paths = GFileUtils.getFiles(pathBean.getSource());
        } else {
            paths.add(pathBean.getSource());
            fileFlag = true;
        }
        try {
            Iterator<String> iterator = paths.iterator();
            while (true) {
                File file;
                do {
                    if (!iterator.hasNext()) {
                        return;
                    }
                    String strfile = iterator.next();
                    file = new File(strfile);
                    String name;
                    String path;
                    String target;
                    if (fileFlag) {
                        if (StringUtils.isNotEmpty(pathBean.getTargetName())) {
                            name = pathBean.getTargetName();
                        } else {
                            name = file.getName();
                        }
                        if (StringUtils.isEmpty(pathBean.getTarget())) {
                            path = name;
                        } else {
                            target = pathBean.getTarget();
                            path = target.concat(File.separator).concat(name);
                        }
                    } else {
                        if (StringUtils.isNotEmpty(pathBean.getTargetName())) {
                            name = pathBean.getTargetName();
                        } else {
                            name = getFilePathName(pathBean.getSource(), strfile);
                        }
                        target = pathBean.getTarget();
                        path = target.concat(File.separator).concat(name);
                    }
                    ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, path);
                    zaos.putArchiveEntry(zipArchiveEntry);
                } while (file.isDirectory());
                BufferedInputStream is = null;
                try {
                    is = new BufferedInputStream(new FileInputStream(file));
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        zaos.write(buffer, 0, len);
                    }
                    zaos.closeArchiveEntry();
                } catch (Exception e) {
                    logger.error("", e);
                    throw new RuntimeException(e);
                } finally {
                    IOGUtils.close(is);
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }

    public static String getFilePathName(String dir, String path) {
        String p = path.replace(dir + File.separator, "");
        p = p.replace("\\", "/");
        return p;
    }
}
