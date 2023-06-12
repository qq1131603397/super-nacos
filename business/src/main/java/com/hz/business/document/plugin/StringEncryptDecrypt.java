package com.hz.business.document.plugin;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author： pt
 * @date： 2023/6/2 10:41
 * @discription
 */
@Component
public class StringEncryptDecrypt {

    @Resource
    private StringEncryptor stringEncryptor;

    public String encrypt(String plainText) {
        // Encrypt
        String encrypted = stringEncryptor.encrypt(plainText);
        System.out.println("Encrypted: " + encrypted);
        return encrypted;
    }

    public String decrypt(String encrypted) {
        // Decrypt
        String decrypted = stringEncryptor.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        return decrypted;
    }

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("gqakj@651243");
        //要加密的数据（数据库的用户名或密码）
        String password = textEncryptor.encrypt("980314");
        System.out.println("password:ENC(" + password + ")");
    }
}

