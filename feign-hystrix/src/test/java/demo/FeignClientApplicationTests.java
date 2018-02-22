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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import demo.FeignClientWithServerListApplicationTests.FallbackRestClient;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
