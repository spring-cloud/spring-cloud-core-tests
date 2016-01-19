package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.trace.DefaultTracer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TraceApplication.class)
@WebAppConfiguration
@DirtiesContext
public class TraceApplicationTests {

	@Autowired
	Tracer traceManager;

	@Test
	public void contextLoads() {
	}

	@Test
	public void traceIsDefaultTrace() {
		assertTrue("trace is wrong type", this.traceManager instanceof DefaultTracer);
	}
}
