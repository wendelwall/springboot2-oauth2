## 使用springboot2.0和oauth2.0实现单点登录

1. 存储token使用的是redis，所以需要本地启动一个redis服务
2. 包含一个认证服务器，一个资源服务器，一个单点登录客户端

### 启动系统后
1. 输入客户端地址 http://localhost:8085/user

2. 进入到认证服务器登录页面

3. 登录完成后，重定向回客户端地址

4. 访问资源服务器,需要将tokenValue的值作为参数传给资源服务器 http://localhost:8088/user?access_token=0ce88c9c-d188-4cfd-b919-e03e5bd4268c

### 使用 postman 实现 password授权模式
1. 允许使用password的授权方式
```
@Configuration
@EnableAuthorizationServer
public class ServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 配置token获取和验证时的策略
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                // secret密码配置从 Spring Security 5.0开始必须以 {加密方式}+加密后的密码 这种格式填写
                /*
                 *   当前版本5新增支持加密方式：
                 *   bcrypt - BCryptPasswordEncoder (Also used for encoding)
                 *   ldap - LdapShaPasswordEncoder
                 *   MD4 - Md4PasswordEncoder
                 *   MD5 - new MessageDigestPasswordEncoder("MD5")
                 *   noop - NoOpPasswordEncoder
                 *   pbkdf2 - Pbkdf2PasswordEncoder
                 *   scrypt - SCryptPasswordEncoder
                 *   SHA-1 - new MessageDigestPasswordEncoder("SHA-1")
                 *   SHA-256 - new MessageDigestPasswordEncoder("SHA-256")
                 *   sha256 - StandardPasswordEncoder
                 */
                .secret("{noop}secret")
                .scopes("all")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .autoApprove(true);
    }
```

2.在postman中输入获取token的地址
![输入图片说明](https://gitee.com/uploads/images/2018/0414/223329_07a9b22c_1305332.png "屏幕截图.png")

3.访问资源服务器的接口，有两种方式

1）作为参数传给后端资源服务器：access_token=68d4a48d-91a3-4e20-9198-cb2b258e9a14

2）作为请求头传给后端资源服务器：
![输入图片说明](https://gitee.com/uploads/images/2018/0414/223542_bf4e0684_1305332.png "屏幕截图.png")

4.刷新access_token的方式
![输入图片说明](https://gitee.com/uploads/images/2018/0417/144334_9f7f8bfa_1305332.png "屏幕截图.png")

5.退出登录
```
    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices tokenServices;

    @RequestMapping(value = "/auth/logout", method = POST)
    @ResponseBody
    public String logout(HttpServletRequest request) {
        // 从请求头获取前端传过来的token，格式：token_type access_token
        String token = request.getHeader("Authorization");
        // 获取access_token
        token =  token.split(" ")[1];
        // 移除token，使得用户退出登录
        if(tokenServices.revokeToken(token)) {
            return "success";
        }
        return "failed";
    }
```
6.实现配置文件对称加密
- 在pom.xml文件中添加依赖
```$xslt
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>1.17</version>
</dependency>
```
- 在测试类中先加密明文
```$xslt
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

public class UtilTests {
    @Test
    public void jasyptTest() {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("bici123456");//application.yml配置的jasypt.encryptor.password
        String encrypted = encryptor.encrypt("root");//要加密的数据（数据库连接的用户名或密码）
        System.out.println(encryptor.decrypt("k4LGQOt0V9uQeB3i5EDiLw=="));
        System.out.println(encrypted);
    }
}
```
- 在application.yml配置文件中配置加密
```$xslt
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/auth?useSSL=false&useUnicode=true&characterEncoding=utf-8
    username: ENC(k4LGQOt0V9uQeB3i5EDiLw==)
    password: ENC(k4LGQOt0V9uQeB3i5EDiLw==)
    driver-class-name: com.mysql.jdbc.Driver
  redis:
    database: 0
    host: 127.0.0.1
    password:
    timeout: 8000
    port: 6379
jasypt:
  encryptor:
    password: bici123456
```