package demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootstrapClientApplication.class)
@WebIntegrationTest("server.port=0")
@DirtiesContext
public class BootstrapClientApplicationTests {

	@Autowired
	private ServerProperties server;

	@Test
	public void contextLoads() throws Exception {
		assertEquals(2, this.server.getTomcat().getMaxThreads());
		// The application.yml is never read because spring.config.name=sample
		assertEquals("application", this.server.getDisplayName());
	}

}
