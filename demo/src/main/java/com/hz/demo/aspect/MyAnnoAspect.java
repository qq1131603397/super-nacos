package com.hz.demo.aspect;

import com.hz.demo.anno.MyAnno;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author： pt
 * @date： 2022/12/30 14:00
 * @discription
 */
@Aspect
@Component
public class MyAnnoAspect {

    @Pointcut(value = "@annotation(com.hz.demo.anno.MyAnno)")
    private void logPointCut(){}

    @Before(value = "@annotation(com.hz.demo.anno.MyAnno)")
    public void before() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // do something
        Map<String, String[]> map = request.getParameterMap();
    }

    @After(value = "@annotation(com.hz.demo.anno.MyAnno)")
    public void after() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        // do something

    }


}
