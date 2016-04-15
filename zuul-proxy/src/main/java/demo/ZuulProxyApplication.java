package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@RestController
public class ZuulProxyApplication {

	@RequestMapping("/good")
	public String good(@RequestParam(required = false) String value) {
		return "GOOD" + (value == null ? "" : " " + value) + "!";
	}

	@RequestMapping("/good/stuff")
	public String stuff() {
		return "STUFF!";
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyApplication.class, args);
	}
}
