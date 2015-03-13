package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloClientApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class HelloClientApplicationTests {

	@Value("${local.server.port}")
	int port;

	@Test
	public void contextLoads() throws Exception {
	}
	
	@Test
	@Ignore
	/*
	 * Unignore this if you have the eureka and "simple" servers running in separate
	 * processes (Eureka singletons will prevent you from running all apps in the same
	 * process). You also need to pause (e.g. set debug stop) while HelloClient registers
	 * with Eureka (only registered services can access the registry).
	 */
	public void clientConnects() throws Exception {
		ResponseEntity<String> response = new TestRestTemplate().getForEntity(
				"http://localhost:" + port, String.class);
		assertNotNull("response was null", response);
		assertEquals("Wrong status code", HttpStatus.OK, response.getStatusCode());
		assertEquals("Hello", response.getBody());
	}

}
