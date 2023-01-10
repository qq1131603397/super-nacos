package com.hz.demo.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.util.Collection;

/**
 * @author： pt
 * @date： 2023/1/9 10:36
 * @discription
 */
public class FileUtil {

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

    public static void main(String[] args) {
        String path = "D:\\globalFileStorage";
        Collection<File> fils = listFilesAndDirs(path, null, true);
        for (File file : fils) {
            System.out.println(file.getPath());
        }
    }
}
