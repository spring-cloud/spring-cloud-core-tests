package demo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Spencer Gibb
 * @author Dave Syer
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StandaloneClientApplication implements CommandLineRunner {

	private static Log logger = LogFactory.getLog(StandaloneClientApplication.class);

	@Autowired
	private DiscoveryClient client;

	@Override
	public void run(String... args) throws Exception {
		List<String> services = client.getServices();
		logger.info("Services: " + services );
	}

	public static void main(String[] args) {
		SpringApplication.run(StandaloneClientApplication.class, args);
	}
}
