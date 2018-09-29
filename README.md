# authing-java-sdk

----------

Authing SDK for JAVA 编写于 JDK 1.8

[官方文档请点击这里](https://docs.authing.cn)。

## 安装

----------

#### maven

计划最近开始支持 maven 安装

## 开始使用

----------

``` java
Client client=new Client(clientId,secret);
        Option option=Option.builder()
                .putOption("xxxx",value)
                .putOption("xxxx",value)
                .putOption("xxxx",value)
                .build();
        String response= client.xxxxxx(option);
```

[怎样获取 Client ID ?](https://docs.authing.cn/#/quick_start/howto)。

获取 Client ID 和 Client Secret，请[点击这里](https://docs.authing.cn/#/quick_start/howto)。

## 错误处理

----------

统一使用 try...catch 处理错误

了解更多报错的详情，请查看[错误代码列表](https://docs.authing.cn/#/quick_start/error_code)。

[接口相关文档请点击这里](https://docs.authing.cn/#/user_service/add_user)。

