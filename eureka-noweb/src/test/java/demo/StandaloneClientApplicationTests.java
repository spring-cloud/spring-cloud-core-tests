package demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class StandaloneClientApplicationTests {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Test
	public void testDiscoveryClientIsEureka() {
		assertTrue("discoveryClient is wrong instance type", discoveryClient instanceof CompositeDiscoveryClient);
		assertTrue("composite discovery client should have a Eureka client with higher precendence"
				, ((CompositeDiscoveryClient)discoveryClient).getDiscoveryClients().get(0) instanceof EurekaDiscoveryClient);
	}

}
