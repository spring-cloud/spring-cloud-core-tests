package demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanListener;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZipkinApplication.class)
@WebAppConfiguration
@DirtiesContext
public class ZipkinApplicationTests {

	@Autowired
	ZipkinSpanListener listener;

	@Test
	public void contextLoads() {
	}

	@Test
	public void listenerIsInjected() {
		assertTrue("listener is wrong type", this.listener instanceof ZipkinSpanListener);
	}
}
