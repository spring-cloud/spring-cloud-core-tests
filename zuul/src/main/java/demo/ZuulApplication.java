package demo;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@SpringBootApplication
@EnableZuulServer
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
}

@Component
class SampleStaticResponseFilter extends ZuulFilter {

	private static final String URI = "/static";

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		String path = RequestContext.getCurrentContext().getRequest().getRequestURI();
		if (checkPath(path))
			return true;
		if (checkPath("/" + path))
			return true;
		return false;
	}

	private boolean checkPath(String path) {
		return URI.equals(path);
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		// Set the default response code for static filters to be 200
		ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
		// first StaticResponseFilter instance to match wins, others do not set body
		// and/or status
		if (ctx.getResponseBody() == null) {
			ctx.setResponseBody("Hello World");
			ctx.setSendZuulResponse(false);
		}
		return null;
	}
}