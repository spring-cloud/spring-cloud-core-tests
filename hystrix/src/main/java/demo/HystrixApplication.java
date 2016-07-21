package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableCircuitBreaker
@RestController
public class HystrixApplication {

	@Bean
	public MyService myService() {
		return new MyService();
	}

	@Autowired
	private MyService myService;

	@RequestMapping("/")
	public String ok() {
		return this.myService.ok();
	}

	@RequestMapping("/fail")
	public String fail(@RequestHeader(name = "X-No-Fail", required = false) String noFail) {
		if (noFail != null) {
			return this.myService.ok();
		}
		return this.myService.fail();
	}

	public static void main(String[] args) {
		SpringApplication.run(HystrixApplication.class, args);
	}
}
