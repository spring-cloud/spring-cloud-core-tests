package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Spencer Gibb
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@LoadBalancerClient(name = "hello", configuration = HelloClientApplication.HelloClientConfiguration.class)
public class HelloClientApplication {
	@Autowired
	HelloClient client;

	@RequestMapping("/")
	public String hello() {
		return client.hello();
	}

	@RequestMapping("/world")
	public String world() {
		return "hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

	@FeignClient("hello")
	interface HelloClient {
		@RequestMapping(value = "/world", method = GET)
		String hello();
	}
	// Load balancer with fixed server list for "hello" pointing to example.com
	static class HelloClientConfiguration {

		@Bean
		@Lazy
		public ServiceInstanceListSupplier myServiceInstanceListSupplier(Environment env) {
			//because of this, it doesn't use eureka to lookup the server,
			// but the classpath is tested
			Integer port = env.getProperty("local.server.port", Integer.class);
			return ServiceInstanceListSupplier.fixed(env)
					.instance("localhost", port, "hello").build();
		}

	}

}

