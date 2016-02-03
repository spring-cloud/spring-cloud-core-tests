package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dave Syer
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaClientApplication {

	@RequestMapping("/good")
	public String good(@RequestParam MultiValueMap<String,String> params) {
		System.err.println(params);
		return "GOOD!";
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}
