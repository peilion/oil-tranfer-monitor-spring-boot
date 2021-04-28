package cn.peilion.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class InfoJarApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfoJarApplication.class, args);
    }
}
