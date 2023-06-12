package com.hz.business.document.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 * @author： pt
 * @date： 2023/6/2 10:51
 * @discription
 */
public class JasyptUtil {

    private static String jasyotPassword = "Gqakj@651243";

    /**
     * 加密
     * @param plaintext 明文密码     * @return
     */
    public static String encrypt(String plaintext) {
        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        // 算法类型
        config.setAlgorithm("PBEWithMD5AndDES");
        //生成秘钥的公钥
        config.setPassword(jasyotPassword);
        //应用配置
        encryptor.setConfig(config);
        //加密
        return encryptor.encrypt(plaintext);
    }

    /**
     * 解密
     *
     * @param ciphertext 待解密秘钥
     * @return
     */
    public static String decrypt(String ciphertext) {
        //加密工具
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        //加密配置
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        //生成秘钥的公钥
        config.setPassword(jasyotPassword);
        //应用配置
        encryptor.setConfig(config);
        //解密
        return encryptor.decrypt(ciphertext);
    }

    public static void main(String[] args) {
        String pwd = "980314";
        System.out.println("password:ENC(" + encrypt(pwd) + ")");
    }
}
