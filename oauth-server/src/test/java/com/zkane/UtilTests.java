package com.zkane;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: 594781919@qq.com
 * @date: 2018/4/24
 */
public class UtilTests {
    @Test
    public void jasyptTest() {
//        BasicTextEncryptor encryptor = new BasicTextEncryptor();
//        // application.yml配置的jasypt.encryptor.password
//        encryptor.setPassword("abc123");
//        // 对root进行加密操作
//        System.out.println(encryptor.encrypt("root"));
//        // 对密码进行加密操作
//        System.out.println(encryptor.encrypt("sqlzyydxe"));
//        // 进行解密操作
//        System.out.println(encryptor.decrypt("iMlafBuZWQONnlTJ+DZB3jMGfs1Y5zju"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("client"));
        System.out.println(encoder.encode("123456"));
    }
}
