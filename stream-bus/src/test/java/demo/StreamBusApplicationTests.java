package demo;

import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.bus.BusBridge;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StreamBusApplicationTests.TestConfig.class, StreamBusApplication.class}, properties = "spring.cloud.bus.destination=you-in-0")
@DirtiesContext
public class StreamBusApplicationTests {

	@Autowired
	private BusBridge bus;

	@Autowired
	private MyListener myListener;

	@Test
	public void business() throws Exception {
		RefreshRemoteApplicationEvent event = new RefreshRemoteApplicationEvent(this, "me", "you");
		this.bus.send(event);
		RefreshRemoteApplicationEvent event1 = myListener.refreshRemoteApplicationEvent;
		assertThat(event1).isEqualTo(event);
	}

	@Configuration
	static class TestConfig {
		@Bean(name = "you")
		MyListener myListener() {
			return new MyListener();
		}
	}
}

class MyListener implements Consumer<RefreshRemoteApplicationEvent> {

	RefreshRemoteApplicationEvent refreshRemoteApplicationEvent;

	@Override
	public void accept(RefreshRemoteApplicationEvent refreshRemoteApplicationEvent) {
		this.refreshRemoteApplicationEvent = refreshRemoteApplicationEvent;
	}
}
