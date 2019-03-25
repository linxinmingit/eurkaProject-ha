package com.itmuch.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //如果同时在一个方法里使用restTemplate对象和loadBalancerClient对象，如下方法，loadBalancerClient对象去eureka拿去服务的时候会计数一次
    //而restTemplate这时候则会到已经轮询过一次的服务端请求，这就会造成客户端每次都打印一个端口，而服务端每次也是另一个端口打印，看起来轮询似乎失效了，
    // 其实不然，他们依然是轮询交错的，也就是轮询依然是有效果的,只是分别消耗了次数。
    @RequestMapping(value="/consumer")
    public String hi() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("service");
        System.out.println("111" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());
        return restTemplate.getForObject("http://service/hi/1", String.class);
    }

    @RequestMapping(value = "/test")
    public void test()
    {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("service");
        System.out.println("[1111------------]" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

        ServiceInstance serviceInstance1 = this.loadBalancerClient.choose("service111");
        System.out.println("[222-------------]" + ":" + serviceInstance1.getServiceId() + ":" + serviceInstance1.getHost() + ":" + serviceInstance1.getPort());
    }
}