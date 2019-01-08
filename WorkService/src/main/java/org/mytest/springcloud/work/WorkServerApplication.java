package org.mytest.springcloud.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author pc
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class WorkServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkServerApplication.class, args);
    }

}
