package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@FeignClientScan
public class FeignClientApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }
}

@FeignClient(value = "example.com", loadbalance = false)
interface RestClient {
	@RequestMapping(value="/", method=RequestMethod.GET)
	String hello();
}
