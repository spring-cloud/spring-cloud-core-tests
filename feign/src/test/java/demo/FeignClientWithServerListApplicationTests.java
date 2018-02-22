package demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RunWith(SpringRunner.class)
// We disable Hystrix because we are not concerned about testing circuit breakers in this
// test and it eliminates hystrix timeouts from messing with the request
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"myexample.ribbon.listOfServers:example.com", "feign.hystrix.enabled=false" })
@DirtiesContext
public class FeignClientWithServerListApplicationTests {

	@Autowired
	private RestClient client;

	@Test
	public void clientConnects() {
		assertTrue(client.hello().contains("<html"));
	}

	@Configuration
	@EnableAutoConfiguration
	@EnableFeignClients
	protected static class TestApplication {

		public static void main(String[] args) {
			SpringApplication.run(FeignClientApplication.class, args);
		}
	}

	@FeignClient(value = "myexample")
	static interface RestClient {
		@RequestMapping(value = "/", method = RequestMethod.GET)
		String hello();
	}
}
