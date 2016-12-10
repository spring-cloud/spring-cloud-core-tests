package demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TraceApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(TraceApplication.class, args);
	}
}

@RestController
class HomeController {

	private static final Log log = LogFactory.getLog(HomeController.class);
	@Autowired
	private RestTemplate restTemplate;
	@Value("${app.url:http://localhost:${local.server.port:${server.port:8080}}}")
	private String url;

	@RequestMapping("/")
	public String home() {
		log.info("Home");
		String s = this.restTemplate.getForObject(url + "/hi", String.class);
		return "hi/" + s;
	}

}

@RestController
class HiController {

	private static final Log log = LogFactory.getLog(HiController.class);

	@RequestMapping("/hi")
	public String hi() {
		log.info("Hi");
		return "hi";
	}
}