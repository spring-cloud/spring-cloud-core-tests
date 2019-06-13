package demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class BootstrapClientApplicationTests {

	@Autowired
	private ServerProperties server;

	@Test
	public void contextLoads() throws Exception {
		assertEquals(2, this.server.getTomcat().getMaxThreads());
		// The application.yml is never read because spring.config.name=sample
		assertEquals("application", this.server.getServlet().getApplicationDisplayName());
	}

}
