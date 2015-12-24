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
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZuulProxyApplication.class)
@DirtiesContext
@WebIntegrationTest(randomPort=true)
public class ZuulProxyApplicationTests {

	@Autowired
	DiscoveryClient discoveryClient;

	@Value("${local.server.port}")
	int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void ignoredPatternMissing() {
		ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:"+this.port +"/missing", String.class);
		assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}

	@Test
	public void forwardedPatternGood() {
		ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:"+this.port +"/good", String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("GOOD!", result.getBody());
	}

	@Test
	public void forwardedPatternGoodWithPath() {
		ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:"+this.port +"/good/stuff", String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("STUFF!", result.getBody());
	}

	@Test
	public void discoveryClientIsNoop() {
		assertTrue("discoveryClient is wrong type", this.discoveryClient instanceof EurekaDiscoveryClient);
	}
}
