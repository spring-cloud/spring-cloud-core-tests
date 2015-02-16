package demo;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.noop.NoopDiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.DiscoveryNotWebApplicationTests.NoDiscoveryApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NoDiscoveryApplication.class)
public class DiscoveryNotWebApplicationTests {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Test
	public void testDiscoveryClientIsNoop() {
		assertTrue("discoveryClient is wrong instance type",
				discoveryClient instanceof NoopDiscoveryClient);
	}

	/*
	 * This works because the NoopDiscoveryClient is installed if there is no @EnableDiscoveryClient
	 * *and* there is no implementation on the classpath
	 */
	@SpringBootApplication
	protected static class NoDiscoveryApplication implements CommandLineRunner {

		private static Log logger = LogFactory.getLog(NotWebApplication.class);

		@Autowired
		private DiscoveryClient client;

		@Override
		public void run(String... args) throws Exception {
			List<String> services = client.getServices();
			logger.info("Services: " + services);
		}
	}

}
