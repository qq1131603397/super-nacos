package com.hz.demo.anno;

import java.lang.annotation.*;

/**
 * @author Peng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {
}
