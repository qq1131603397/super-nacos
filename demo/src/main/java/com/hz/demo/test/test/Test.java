package com.hz.demo.test.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author： pt
 * @date： 2023/6/1 17:07
 * @discription
 */
public class Test {

    private Pattern pattern = Pattern.compile("\\d+");

    public void regex() {
        Pattern pattern = Pattern.compile("\\d+");
        System.out.println(pattern.matcher("123"));
    }


    public static void main(String[] args) {
        final String regex = "a+?";
        final String string = "aaaaaaaaaa";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        if (matcher.matches()) {
            System.out.println("Full match: " + matcher.group());
        }
    }

}
