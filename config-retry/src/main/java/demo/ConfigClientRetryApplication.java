package demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Spencer Gibb
 * @author Dave Syer
 */
@SpringBootApplication
public class ConfigClientRetryApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigClientRetryApplication.class).properties(
				"spring.cloud.config.enabled=true").run(args);
	}
}
