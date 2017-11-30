package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAdjuster;
import org.springframework.cloud.sleuth.zipkin2.EndpointLocator;
import org.springframework.cloud.sleuth.zipkin2.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import zipkin2.reporter.Reporter;

import java.util.List;


@SpringBootApplication
public class ZipkinApplication {

	private static Logger log = LoggerFactory.getLogger(ZipkinApplication.class);

	// Use this for debugging (or if there is no Zipkin server running on port 9411)
	@Bean
	@ConditionalOnProperty(value="sample.zipkin.enabled", havingValue="false")
	public ZipkinSpanReporter spanCollector(Reporter<zipkin2.Span> reporter, EndpointLocator endpointLocator, Environment environment, List<SpanAdjuster> spanAdjusters) {
		return new ZipkinSpanReporter(reporter, endpointLocator, environment, spanAdjusters) {
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
