package demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.bus.SpringCloudBusClient;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StreamBusApplication.class)
@IntegrationTest
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
		assertEquals(message, this.collector.forChannel(this.source.output()).take());
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
