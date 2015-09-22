package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StandaloneClientApplication.class)
@IntegrationTest
@DirtiesContext
public class StandaloneClientApplicationTests {

	@Autowired
	private ConfigServicePropertySourceLocator locator;

	@Test
	public void contextLoads() throws Exception {
	}

}
