package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FeignEagerInstantiationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignEagerInstantiationApplication.class, args);
    }
}
