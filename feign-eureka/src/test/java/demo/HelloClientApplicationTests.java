package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloClientApplication.class)
//We disable Hystrix because we are not concerned about testing circuit breakers in this test
//and it eliminates hystrix timeouts from messing with the request
@WebIntegrationTest(randomPort = true, value="feign.hystrix.enabled=false")
@DirtiesContext
public class HelloClientApplicationTests {

	@Value("${local.server.port}")
	int port;

	@Test
	public void clientConnects() {
		ResponseEntity<String> response = new TestRestTemplate().getForEntity("http://localhost:" + port, String.class);
		assertNotNull("response was null", response);
		assertEquals("Wrong status code", HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains("<html"));
	}

}
