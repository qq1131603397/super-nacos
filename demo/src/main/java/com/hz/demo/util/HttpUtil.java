package com.hz.demo.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author： pt
 * @date： 2021/9/9 14:38
 * @discription Http请求工具类
 */
public class HttpUtil {

    /**
     * 带参数的post请求
     *
     * @param url   url
     * @param param 参数
     * @return {@link String}
     */
    public static String doPost(String url, String param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(param);
            httpPost.setHeader("content-type", "application/json;charset=UTF-8");
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("消息返回：" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 带参数的get请求
     *
     * @param url   url
     * @param param 参数
     * @return {@link String}
     */
    public static String doGet(String url, JSONObject param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            //创建URLBuilder
            URIBuilder uriBuilder = new URIBuilder(url);
            //设置参数
            param.forEach((key, value) -> uriBuilder.setParameter(key, value.toString()));
            //创建HttpGet对象，设置URL地址
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //解析响应，获取数据
            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            resultString = EntityUtils.toString(httpEntity, "utf8");
            System.out.println("消息返回：" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 不带参数的get请求
     *
     * @param url   url
     * @return {@link String}
     */
    public static String doGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            //创建URLBuilder
            URIBuilder uriBuilder = new URIBuilder(url);
            //创建HttpGet对象，设置URL地址
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //解析响应，获取数据
            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            resultString = EntityUtils.toString(httpEntity, "utf8");
            System.out.println("消息返回：" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

}
