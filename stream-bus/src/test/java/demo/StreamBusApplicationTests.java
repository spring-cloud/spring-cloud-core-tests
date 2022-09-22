package demo;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.bus.event.EnvironmentChangeRemoteApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = { "management.endpoints.web.exposure.include=*",
		"logging.level.org.springframework.cloud.bus=TRACE", "spring.cloud.bus.id=app:1"})
@Testcontainers
public class StreamBusApplicationTests {
	@Container
	private static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer();

	private static ConfigurableApplicationContext context;

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.rabbitmq.host", rabbitMQContainer::getHost);
		registry.add("spring.rabbitmq.port", rabbitMQContainer::getAmqpPort);
	}

	@BeforeAll
	static void before() {
		context = new SpringApplicationBuilder(TestConfig.class).properties("server.port=0",
				"spring.rabbitmq.host=" + rabbitMQContainer.getHost(),
				"spring.rabbitmq.port=" + rabbitMQContainer.getAmqpPort(),
				"management.endpoints.web.exposure.include=*", "spring.cloud.bus.id=app:2",
				"spring.autoconfigure.exclude=org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration")
				.run();
	}

	@AfterAll
	static void after() {
		if (context != null) {
			context.close();
		}
	}

	@Test
	void remoteEventsAreSentViaAmqp(@Autowired WebTestClient client, @Autowired TestConfig testConfig)
			throws InterruptedException {
		assertThat(rabbitMQContainer.isRunning());
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "foo");
		map.put("value", "bar");
		client.post().uri("/actuator/busenv").bodyValue(map).exchange().expectStatus().is2xxSuccessful();
		TestConfig remoteTestConfig = context.getBean(TestConfig.class);
		assertThat(remoteTestConfig.latch.await(5, TimeUnit.SECONDS)).isTrue();
		assertThat(testConfig.latch.await(5, TimeUnit.SECONDS)).isTrue();
	}

	@SpringBootConfiguration
	@EnableAutoConfiguration
	static class TestConfig implements ApplicationListener<EnvironmentChangeRemoteApplicationEvent> {

		CountDownLatch latch = new CountDownLatch(1);

		@Override
		public void onApplicationEvent(EnvironmentChangeRemoteApplicationEvent event) {
			latch.countDown();
		}

	}

}
