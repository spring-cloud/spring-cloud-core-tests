package demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.noop.NoopDiscoveryClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZuulProxyApplication.class)
public class ZuulProxyApplicationTests {

	@Autowired
	DiscoveryClient discoveryClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void discoveryClientIsNoop() {
		assertTrue("discoveryClient is wrong type", discoveryClient instanceof NoopDiscoveryClient);
	}
}
