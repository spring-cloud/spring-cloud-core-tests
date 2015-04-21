package demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Spencer Gibb
 * @author Dave Syer
 */
@SpringBootApplication
public class StandaloneClientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(StandaloneClientApplication.class).properties(
				"spring.cloud.config.enabled=true").run(args);
	}
}
