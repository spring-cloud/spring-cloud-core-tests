/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package apps;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
public class SimpleApplication {

	@Autowired
	private HelloService service;

	@RequestMapping("/")
	public String hello() {
		return this.service.hello();
	}

	@RequestMapping("/session")
	public String session(HttpSession session) {
		return session.getId();
	}

	@RequestMapping("/fail")
	public String fail() {
		return this.service.fail();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(SimpleApplication.class).properties(
				"spring.config.name:simple").run(args);
	}

	@Service
	public static class HelloService {

		@HystrixCommand(fallbackMethod="fallback")
		public String hello() {
			return "Hello World";
		}

		@HystrixCommand(fallbackMethod="fallback")
		public String fail() {
			throw new RuntimeException("Planned");
		}

		public String fallback() {
			return "Fallback";
		}

	}

}
