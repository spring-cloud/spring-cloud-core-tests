package demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Spencer Gibb
 */
public class MyService {

	@HystrixCommand(fallbackMethod = "fallback")
	public String ok() {
		return "OK";
	}

	@HystrixCommand(fallbackMethod = "fallback")
	public String fail() {
		throw new RuntimeException("fail now");
	}

	public String fallback() {
		return "from the fallback";
	}
}
