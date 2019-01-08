package org.mytest.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author pc
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrixDashboard
public class SpringZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringZuulApplication.class, args);
    }

    @Bean
    public MyFilter myFilter() {
        return new MyFilter();
    }

}
