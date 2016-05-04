package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.noop.NoopDiscoveryClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZuulApplication.class)
@DirtiesContext
@WebIntegrationTest(randomPort = true)
public class ZuulApplicationTests {

	@Autowired
	DiscoveryClient discoveryClient;

	@Value("${local.server.port}")
	int port;

	@Test
	public void contextLoads() {
		assertEquals("Hello World", new TestRestTemplate()
				.getForObject("http://localhost:" + this.port + "/static", String.class));
	}

	@Test
	public void discoveryClientIsNoop() {
		assertTrue("discoveryClient is wrong type",
				this.discoveryClient instanceof NoopDiscoveryClient);
	}
}
