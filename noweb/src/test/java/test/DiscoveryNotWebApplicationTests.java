package test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

import demo.NotWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscoveryNotWebApplicationTests {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Test
	public void testDiscoveryClientIsSimple() {
		assertTrue("discoveryClient is wrong instance type",
				discoveryClient instanceof CompositeDiscoveryClient);
	}

	/*
	 * This works because the SimpleDiscoveryClient is installed if there is no @EnableDiscoveryClient
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
