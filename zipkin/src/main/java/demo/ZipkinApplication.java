package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import zipkin.Span;

@SpringBootApplication
public class ZipkinApplication {

	private static Logger log = LoggerFactory.getLogger(ZipkinApplication.class);

	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}

	// Use this for debugging (or if there is no Zipkin server running on port 9411)
	@Bean
	@ConditionalOnProperty(value="sample.zipkin.enabled", havingValue="false")
	public ZipkinSpanReporter spanCollector() {
		return new ZipkinSpanReporter() {
			@Override
			public void report(Span span) {
				log.info("Reporting span [{}]", span);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ZipkinApplication.class, args);
	}
}
