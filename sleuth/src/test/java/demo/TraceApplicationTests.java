package demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.sleuth.Trace;
import org.springframework.cloud.sleuth.trace.DefaultTrace;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TraceApplication.class)
@WebAppConfiguration
@DirtiesContext
public class TraceApplicationTests {

	@Autowired
	Trace trace;

	@Test
	public void contextLoads() {
	}

	@Test
	public void traceIsDefaultTrace() {
		assertTrue("trace is wrong type", this.trace instanceof DefaultTrace);
	}
}
