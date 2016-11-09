package demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * @author Ryan Baxter
 */
@SpringBootApplication
@EnableZuulProxy
@RestController
@RibbonClient(name = "self", configuration = TomcatApp.SelfRibbonClientConfiguration.class)
public class TomcatApp {
	@Value("${server.port}")
	int port;

	public static void main(String[] args) {
		SpringApplication.run(TomcatApp.class, args);
	}

	@RequestMapping("/good")
	public String good(@RequestParam(required = false) String value) {
		return "GOOD" + (value == null ? "" : " " + value) + "!";
	}

	@Configuration
	class SelfRibbonClientConfiguration {

		@Bean
		public ILoadBalancer ribbonLoadBalancer() {
			//because of this, it doesn't use eureka to lookup the server,
			// but the classpath is tested
			BaseLoadBalancer balancer = new BaseLoadBalancer();
			balancer.setServersList(Arrays.asList(new Server("localhost", port)));
			return balancer;
		}

	}
}
