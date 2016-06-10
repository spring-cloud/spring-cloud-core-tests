package demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

/**
 * @author brenuart
 */
@ComponentScan(
// Exclude @Configuration classes that should be included only in sub contexts created
// by Ribbon's SpringClientFactory.
excludeFilters = { @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*RibbonConfig") })

@SpringBootApplication
@RibbonClients(defaultConfiguration = MyDefaultRibbonConfig.class)
@Configuration
public class RibbonDefaultConfigApplication {

	@Autowired
	private SpringClientFactory clientFactory;

	/**
	 * Throws exception if the SpringClientFactory doesn't return a balancer with a server
	 * list of the expected type.
	 *
	 */
	@PostConstruct
	public void test() throws Exception {
		@SuppressWarnings("unchecked")
		ZoneAwareLoadBalancer<Server> lb = (ZoneAwareLoadBalancer<Server>) this.clientFactory.getLoadBalancer("baz");

		ServerList<Server> serverList = lb.getServerListImpl();
		if (!(serverList instanceof MyDefaultRibbonConfig.BazServiceList)) {
			throw new Exception("wrong server list type");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication app = new SpringApplicationBuilder(RibbonDefaultConfigApplication.class).build();
		app.run(args);
	}
}
