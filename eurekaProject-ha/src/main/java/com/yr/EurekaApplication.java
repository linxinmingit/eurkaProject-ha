package com.yr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 参考网址：https://www.cnblogs.com/junjiang3/p/9061867.html
 * 测试高可用方案：
 * 首先启动eurekaProject-ha 和 eurekaProject1-ha两个服务中心，然后启动providerProject项目,
 * 最后启动消费者，这个时候连接的应该是eurekaProject-ha项目，将它断开，那么服务如果会去找eurekaProject1-ha，高可用测试成功
 * 测试不同区域方案：
 * 首先启动eurekaProject-ha区域改为beijing，然后启动eurekaProject1-ha将区域改为nanjing,这个时候连接生产者到eureka上，如果
 * 只有一端有则测试成功，区域之间不共享。
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
