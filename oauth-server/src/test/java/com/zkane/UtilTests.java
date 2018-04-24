package com.zkane;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

/**
 * @author: keluosi@bicitech.cn
 * @date: 2018/4/24
 */
public class UtilTests {
    @Test
    public void jasyptTest() {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("bici123456");//application.yml配置的jasypt.encryptor.password
        String encrypted = encryptor.encrypt("root");//要加密的数据（数据库连接的用户名或密码）
        System.out.println(encryptor.decrypt("k4LGQOt0V9uQeB3i5EDiLw=="));
        System.out.println(encrypted);
        Class<String> stringClass = String.class;
    }
}
