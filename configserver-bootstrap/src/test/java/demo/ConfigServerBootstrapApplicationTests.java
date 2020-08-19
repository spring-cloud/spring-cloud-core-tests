package demo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class ConfigServerBootstrapApplicationTests {

	@Test
	@Ignore // see https://github.com/spring-cloud/spring-cloud-core-tests/issues/39
	public void contextLoads() {
	}

}
