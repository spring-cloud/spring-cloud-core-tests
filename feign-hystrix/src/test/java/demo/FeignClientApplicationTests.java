package demo;

import demo.FeignClientApplicationTests.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import demo.FeignClientWithServerListApplicationTests.FallbackRestClient;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class FeignClientApplicationTests {

	@Autowired
	private UrlRestClient client;

	@Configuration
	@EnableAutoConfiguration
	@EnableFeignClients
	protected static class TestApplication {

		@Bean
		public FallbackClient fallback() {
			return new FallbackClient();
		}

		@Bean
		public FallbackRestClient fallbackRestClient() {
			return new FallbackRestClient();
		}

		public static void main(String[] args) {
			SpringApplication.run(FeignClientApplication.class, args);
		}
	}
	
	@Test
	public void clientConnects() {
		assertTrue(client.hello().contains("<html"));
	}

}
