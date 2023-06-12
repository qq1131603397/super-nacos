package com.hz.business.document.plugin;

import com.alibaba.druid.pool.DruidDataSource;
import com.hz.business.document.util.EncryptorUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * @author： pt
 * @date： 2023/6/2 13:37
 * @discription
 */
public class DruidDataSourceEncyptBeanPostProcessor implements BeanPostProcessor {

    private CustomEncryptProperties customEncryptProperties;

    private DataSourceProperties dataSourceProperties;

    public DruidDataSourceEncyptBeanPostProcessor(CustomEncryptProperties customEncryptProperties, DataSourceProperties dataSourceProperties) {
        this.customEncryptProperties = customEncryptProperties;
        this.dataSourceProperties = dataSourceProperties;
    }



    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof DruidDataSource){
            if(customEncryptProperties.isEnabled()){
                DruidDataSource druidDataSource = (DruidDataSource)bean;
                System.out.println("--------------------------------------------------------------------------------------");
                String username = dataSourceProperties.getUsername();
                druidDataSource.setUsername(EncryptorUtils.decode(customEncryptProperties.getSecretKey(),username));
                System.out.println("--------------------------------------------------------------------------------------");
                String password = dataSourceProperties.getPassword();
                druidDataSource.setPassword(EncryptorUtils.decode(customEncryptProperties.getSecretKey(),password));
                System.out.println("--------------------------------------------------------------------------------------");
                String url = dataSourceProperties.getUrl();
                druidDataSource.setUrl(EncryptorUtils.decode(customEncryptProperties.getSecretKey(),url));
                System.out.println("--------------------------------------------------------------------------------------");
            }

        }
        return bean;
    }
}
