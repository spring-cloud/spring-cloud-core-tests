package demo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.SocketUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Ryan Baxter
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class TomcatAppTests {

	@BeforeClass
	public static void beforeClass() {
		int randomPort = SocketUtils.findAvailableTcpPort();
		System.setProperty("server.port", String.valueOf(randomPort));
	}

	@AfterClass
	public static void afterClass() {
		System.clearProperty("server.port");
	}

	@Autowired
	SpringClientFactory clientFactory;

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	public void ribbonTest() throws Exception {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/self/good", String.class);
		assertThat(responseEntity.getBody(), is("GOOD!"));
	}

	@Test
	public void sendResponseTest() throws Exception {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("/goodurl", String.class);
		assertThat(responseEntity.getBody(), is("GOOD!"));
		assertThat(responseEntity.getHeaders().get("Content-Length").get(0), is("5"));
	}
}
