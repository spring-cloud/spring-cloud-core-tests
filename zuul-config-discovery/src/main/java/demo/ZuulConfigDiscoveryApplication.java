package demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulConfigDiscoveryApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZuulConfigDiscoveryApplication.class).web(true).run(args);
	}

}
