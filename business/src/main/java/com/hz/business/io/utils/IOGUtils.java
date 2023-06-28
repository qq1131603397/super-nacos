package com.hz.business.io.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author： pt
 * @date： 2023/6/28 10:10
 * @discription
 */
public class IOGUtils {

    private static final Logger logger = LoggerFactory.getLogger(IOGUtils.class);

    public IOGUtils() {
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.info("", e);
            }
        }
    }
}
