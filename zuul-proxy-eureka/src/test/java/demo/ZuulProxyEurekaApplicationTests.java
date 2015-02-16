package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZuulProxyEurekaApplication.class)
@WebAppConfiguration
@DirtiesContext
public class ZuulProxyEurekaApplicationTests {

	@Autowired
	DiscoveryClient discoveryClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void discoveryClientIsEureka() {
		assertTrue("discoveryClient is wrong type", discoveryClient instanceof EurekaDiscoveryClient);
	}

}
