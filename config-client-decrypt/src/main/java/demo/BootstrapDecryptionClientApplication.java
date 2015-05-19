package demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.client.ConfigClientProperties;

/**
 * @author Dave Syer
 */
@SpringBootApplication
public class BootstrapDecryptionClientApplication implements CommandLineRunner {
	
	private static Log logger = LogFactory.getLog(BootstrapDecryptionClientApplication.class);
	
	@Autowired
	private ConfigClientProperties config;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Config Server URI: " + config.getUri());
	}

	public static void main(String[] args) {
		SpringApplication.run(BootstrapDecryptionClientApplication.class, args);
	}
}
