package demo;

import brave.Tracer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class TraceApplicationTests {

	@Autowired(required = false)
	Tracer traceManager;

	@Test
	public void contextLoads() {
	}

	@Test
	public void traceIsDefaultTrace() {
		assertNotNull("Tracer was null", this.traceManager);
	}
}
