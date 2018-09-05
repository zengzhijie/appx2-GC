package com.dreawer.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.dreawer.responsecode.rcdt.ResponseCode;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
public class GoodsApplication {

    public static void main(String[] args) {
    	
        SpringApplication.run(GoodsApplication.class, args);
        ResponseCode.initNamespace("gc");
    }
    
    @Bean // 定义REST客户端，RestTemplate实例
    @LoadBalanced // 开启负载均衡的能力
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1500);// 设置超时
        requestFactory.setReadTimeout(2000);
        return new RestTemplate(requestFactory);
    }
}
