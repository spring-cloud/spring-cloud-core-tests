package demo;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * @author Spencer Gibb
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@RibbonClient(name = "hello", configuration = HelloRibbonClientConfiguration.class)
public class HelloClientApplication {
	@Autowired
	HelloClient client;

	@RequestMapping("/")
	public String hello() {
		return client.hello();
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

	@FeignClient("hello")
	interface HelloClient {
		@RequestMapping(value = "/", method = GET)
		String hello();
	}
}

// Load balancer with fixed server list for "hello" pointing to example.com
@Configuration
class HelloRibbonClientConfiguration {

	@Bean
	public ILoadBalancer ribbonLoadBalancer() {
		//because of this, it doesn't use eureka to lookup the server,
		// but the classpath is tested
		BaseLoadBalancer balancer = new BaseLoadBalancer();
		balancer.setServersList(Arrays.asList(new Server("example.com", 80)));
		return balancer;
	}

}
