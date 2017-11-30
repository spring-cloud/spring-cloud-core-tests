package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class HelloClientApplicationTests {

	@LocalServerPort
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
