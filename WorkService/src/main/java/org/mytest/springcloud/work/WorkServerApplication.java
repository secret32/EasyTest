package org.mytest.springcloud.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author pc
 */
@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class WorkServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkServerApplication.class, args);
    }

}
