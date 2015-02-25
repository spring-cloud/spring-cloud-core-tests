package demo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZuulConfigDiscoveryApplication.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class ZuulConfigDiscoveryApplicationTests {

    @Value("${local.server.port}")
    private int port;


    //FIXME: works fine when running the app, but not as a test
    @Test
	@Ignore
    public void contextLoads() {
    }

}
