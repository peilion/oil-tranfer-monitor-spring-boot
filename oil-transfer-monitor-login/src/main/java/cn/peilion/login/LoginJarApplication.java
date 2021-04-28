package cn.peilion.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoginJarApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginJarApplication.class, args);
    }
}
