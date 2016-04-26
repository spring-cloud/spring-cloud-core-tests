package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author Dave Syer
 */
@SpringBootApplication
@EnableBinding(Source.class)
public class StreamBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamBusApplication.class, args);
	}
}