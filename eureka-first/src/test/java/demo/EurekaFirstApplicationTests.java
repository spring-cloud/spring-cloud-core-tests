package demo;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class EurekaFirstApplicationTests {

	@Autowired
	DiscoveryClient discoveryClient;

	@Test
	@Ignore
	public void contextLoads() {
	}

	@Test
	@Ignore
	public void discoveryClientIsEureka() {
		assertTrue("discoveryClient is wrong type: " + discoveryClient, discoveryClient instanceof EurekaDiscoveryClient);
	}

}
