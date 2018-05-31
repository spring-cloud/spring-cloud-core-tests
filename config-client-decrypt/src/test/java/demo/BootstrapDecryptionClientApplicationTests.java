package demo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class BootstrapDecryptionClientApplicationTests {

	@Autowired
	private ConfigClientProperties config;

	@Autowired
	private ConfigServicePropertySourceLocator locator;

	@Test
	public void contextLoads() throws Exception {
		assertArrayEquals(new String[]{"http://localhost:8888"}, this.config.getUri());
		assertNotNull(this.locator);
	}

}
