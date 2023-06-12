package com.hz.business.document.plugin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author： pt
 * @date： 2023/6/2 13:40
 * @discription
 */
@Component
@ConfigurationProperties(prefix = "custom")
public class CustomEncryptProperties {

    private String secretKey;

    private boolean enabld;

    public String getSecretKey() {
        return secretKey;
    }

    public boolean isEnabled() {
        return enabld;
    }
}
