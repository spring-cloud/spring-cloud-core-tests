package demo;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ZuulApplicationTests {

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Value("${local.server.port}")
	int port;

	@Test
	@Ignore
	public void useRestTemplate() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("http://localhost:" + this.port, String.class);
		assertEquals(HttpStatus.FOUND, entity.getStatusCode());
		assertEquals("http://localhost:" + this.port + "/login", entity.getHeaders().getLocation().toString());
	}

}
