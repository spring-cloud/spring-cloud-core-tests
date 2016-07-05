package demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZuulApplication.class)
@IntegrationTest({ "debug=true", "server.port=0" })
@WebAppConfiguration
public class ZuulApplicationTests {

	private RestTemplate restTemplate = new TestRestTemplate();

	@Value("${local.server.port}")
	int port;

	@Test
	public void useRestTemplate() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("http://localhost:" + this.port, String.class);
		assertEquals(HttpStatus.FOUND, entity.getStatusCode());
		assertEquals("http://localhost:" + this.port + "/login", entity.getHeaders().getLocation().toString());
	}

}
