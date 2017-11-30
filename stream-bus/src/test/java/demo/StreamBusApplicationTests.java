package demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.bus.SpringCloudBusClient;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class StreamBusApplicationTests {

	@Autowired
	private Source source;

	@Autowired
	private SpringCloudBusClient bus;

	@Autowired
	private MessageCollector collector;

	@Test
	public void streaminess() throws Exception {
		Message<String> message = MessageBuilder.withPayload("Hello").build();
		this.source.output().send(message);
		Message<?> received = this.collector.forChannel(this.source.output()).take();
		assertThat(received.getPayload()).isEqualTo(message.getPayload());
		assertThat(received.getHeaders()).containsKeys("contentType", "id", "timestamp");
	}

	@Test
	public void business() throws Exception {
		Message<RefreshRemoteApplicationEvent> message = MessageBuilder
				.withPayload(new RefreshRemoteApplicationEvent(this, "me", "you"))
				.build();
		this.bus.springCloudBusOutput().send(message);
		String payload = (String) this.collector
				.forChannel(this.bus.springCloudBusOutput()).take().getPayload();
		assertTrue("Wrong payload: " + payload, payload.contains("\"type\""));
	}

}
