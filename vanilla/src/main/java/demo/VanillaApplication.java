package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

/**
 * @author Dave Syer
 */
@SpringBootApplication
public class VanillaApplication {

	@Bean
	@RefreshScope
	public Runnable runner() {
		return () -> System.err.println("*****");
	}

	public static void main(String[] args) {
		SpringApplication.run(VanillaApplication.class, args);
	}
}
