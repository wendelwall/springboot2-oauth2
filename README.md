## 使用springboot2.0和oauth2.0实现单点登录

1. 存储token使用的是redis，所以需要本地启动一个redis服务
2. 包含一个认证服务器，一个资源服务器，一个单点登录客户端

## 启动系统后
1. 输入 http://localhost:8085/user
![输入图片说明](https://gitee.com/uploads/images/2018/0409/141418_2a3845ce_1305332.png "屏幕截图.png")
2. 进入到认证服务器登录页面
![输入图片说明](https://gitee.com/uploads/images/2018/0409/141506_bf39dfc5_1305332.png "屏幕截图.png")
3. 登录完成后，返回到初始页面
![输入图片说明](https://gitee.com/uploads/images/2018/0409/141655_d8533afd_1305332.png "屏幕截图.png")
4. 访问资源服务器，需要将tokenValue的值作为参数传给资源服务器
![输入图片说明](https://gitee.com/uploads/images/2018/0409/141810_0ce4ccd2_1305332.png "屏幕截图.png")