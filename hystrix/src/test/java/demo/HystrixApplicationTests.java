package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.InputStream;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HystrixApplication.class)
@IntegrationTest("server.port=0")
@WebAppConfiguration
@DirtiesContext
public class HystrixApplicationTests {

	@Value("${local.server.port}")
	int port;

	@Test
	public void testOk() {
		ResponseEntity<String> response = new TestRestTemplate().getForEntity("http://localhost:" + port, String.class);
		assertNotNull("response was null", response);
		assertEquals("Bad status code", HttpStatus.OK, response.getStatusCode());
		assertEquals("Wrong response text", "OK", response.getBody());
	}

	@Test
	public void testFallback() {
		ResponseEntity<String> response = new TestRestTemplate().getForEntity("http://localhost:" + port+"/fail", String.class);
		assertNotNull("response was null", response);
		assertEquals("Bad status code", HttpStatus.OK, response.getStatusCode());
		assertEquals("Wrong response text", "from the fallback", response.getBody());
	}

	@Test
	public void hystrixStreamWorks() throws Exception {
		String url = "http://localhost:" + port;
		//you have to hit a Hystrix circuit breaker before the stream sends anything
		ResponseEntity<String> response = new TestRestTemplate().getForEntity(url, String.class);
		assertEquals("bad response code", HttpStatus.OK, response.getStatusCode());

		URL hystrixUrl = new URL(url + "/hystrix.stream");
		InputStream in = hystrixUrl.openStream();
		byte[] buffer = new byte[1024];
		in.read(buffer);
		String contents = new String(buffer);
		assertTrue(contents.contains("ping"));
		in.close();
	}
}
