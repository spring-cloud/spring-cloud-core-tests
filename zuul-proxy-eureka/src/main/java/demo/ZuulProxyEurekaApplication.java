package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulProxyEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyEurekaApplication.class, args);
	}
}
