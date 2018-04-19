## 1.背景
项目由springboot1.5.X升级到springboot2.0.0后，导致各组件API以及依赖包发生了变化。

完整项目demo：https://gitee.com/zkane/springboot2-oauth2.git

## 2.spring security
Spring Security 从入门到进阶系列教程网址：http://www.spring4all.com/article/428
- spring security架构图
![输入图片说明](https://gitee.com/uploads/images/2018/0419/104945_222198e1_1305332.png "屏幕截图.png")
- 认证过程
![输入图片说明](https://gitee.com/uploads/images/2018/0419/105052_53ea1406_1305332.png "屏幕截图.png")

## 3.OAuth2
- OAuth2基础概念网址：http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html
- OAuth2分为3个步骤：①认证服务器②资源服务器③第三方应用
- OAuth2有4种授权模式：①授权码模式②简化模式③密码模式④客户端模式

## 4.使用springboot2+oauth2踩的坑
- 项目搭建参考网址：

https://blog.csdn.net/qq_19671173/article/details/79748422

http://wiselyman.iteye.com/blog/2411813
### 坑1：在pom.xml文件中导入依赖包发生变化
```
        <!-- springboot2.0已经将oauth2.0与security整合在一起 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <!-- 由于一些注解和API从spring security5.0中移除，所以需要导入下面的依赖包  -->
        <dependency>
            <groupId>org.springframework.security.oauth.boot</groupId>
            <artifactId>spring-security-oauth2-autoconfigure</artifactId>
            <version>2.0.0.RELEASE</version>
        </dependency>
        <!-- redis相关依赖包 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
```

### 坑2：直接使用RedisTokenStore存储token会出现NoSuchMethodError RedisConnection.set([B[B)V错误
解决方案：自己编写一个MyRedisTokenStore，复制RedisTokenStore类中代码，并将代码中conn.set(accessKey, serializedAccessToken)修改为conn.stringCommands().set(accessKey, serializedAccessToken);

### 坑3：前后端分离时，存在跨域问题
解决方案：在后端注册corsFilter
```
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1
        corsConfiguration.addAllowedOrigin("*");
        // 2
        corsConfiguration.addAllowedHeader("*");
        // 3
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // step 4
        source.registerCorsConfiguration("/**", buildConfig());
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
```
### 坑4：前后端分离，登录页面放在前端时登录的问题
解决方案：授权模式使用password的方式