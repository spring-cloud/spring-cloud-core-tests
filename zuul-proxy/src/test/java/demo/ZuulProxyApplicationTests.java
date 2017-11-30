package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ZuulProxyApplicationTests {

	@Autowired
	DiscoveryClient discoveryClient;

	@LocalServerPort
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
	public void discoveryClientIsComposite() {
		assertTrue("discoveryClient is wrong type", this.discoveryClient instanceof CompositeDiscoveryClient);
	}
}
