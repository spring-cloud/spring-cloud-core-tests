package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HystrixApplication.class)
@IntegrationTest("server.port=0")
@WebAppConfiguration
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
}
