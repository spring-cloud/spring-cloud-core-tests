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
	public String fail(boolean throwSomething) {
		if(throwSomething)
		  throw new RuntimeException("fail now");
		else
		  return "";

	}

	public String fallback() {
		return "from the fallback";
	}

	public String fallback(boolean throwSomething) {
		return "from the fallback";
	}
}
