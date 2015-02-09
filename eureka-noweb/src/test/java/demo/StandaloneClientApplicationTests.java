package demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StandaloneClientApplication.class)
@IntegrationTest
@DirtiesContext
public class StandaloneClientApplicationTests {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Test
	public void testDiscoveryClientIsEureka() {
		assertTrue("discoveryClient is wrong instance type", discoveryClient instanceof EurekaDiscoveryClient);
	}

}
