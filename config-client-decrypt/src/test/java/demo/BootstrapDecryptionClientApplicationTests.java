package demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootstrapDecryptionClientApplication.class)
@IntegrationTest
@DirtiesContext
public class BootstrapDecryptionClientApplicationTests {

	@Autowired
	private ConfigClientProperties config;
	
	@Autowired
	private ConfigServicePropertySourceLocator locator;

	@Test
	public void contextLoads() throws Exception {
		assertEquals("http://localhost:8888", config.getUri());
	}

}
