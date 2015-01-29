package demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FeignClientApplication.class)
@DirtiesContext
public class FeignClientApplicationTests {

	@Autowired
	private RestClient client;
	
	@Test
	public void clientConnects() {
		assertTrue(client.hello().contains("<html"));
	}

}
