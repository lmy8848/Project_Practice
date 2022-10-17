/*
 * 项目名:CloudMusic
 * 作者:麒玖网络
 * 类名:TomcatConfig.java
 * 包名:com.example.demo.config.TomcatConfig
 * 当前修改时间:2022年 07月 14日   13:22:02
 * 上次修改时间:2022年07月12日 16:54:08
 * Copyright (c) 2022. @ 麒玖网络
 */

package com.example.usertest.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//服务端接收请求不会对符号进行转义，Chrome不异常的原因或许是浏览器自身转义了。

/**
 * 解决特殊字符转义
 * @author NJQ-PC
 */
@Configuration
public class TomcatConfig {
    @Bean
    public TomcatServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((Connector connector) -> {
            connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
            connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
        });
        return factory;
    }

}
