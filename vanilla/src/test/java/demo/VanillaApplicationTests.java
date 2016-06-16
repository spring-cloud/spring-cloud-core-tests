package demo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VanillaApplication.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class VanillaApplicationTests {

	@Autowired
	private ContextRefresher refresher;

	@Test
	public void contextLoads() throws Exception {
		assertNotNull(refresher);
	}

}
