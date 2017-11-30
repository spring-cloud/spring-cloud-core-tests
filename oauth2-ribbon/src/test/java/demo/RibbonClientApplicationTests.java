package demo;

import java.net.URI;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.client.ClientHttpRequest;
import org.springframework.mock.web.MockHttpServletRequest;
// import org.springframework.security.oauth2.client.OAuth2RestTemplate;
// import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RibbonClientApplicationTests {

	// FIXME boot 2.0.0 spring-security 5.0 missing oauth
	/*@Autowired
	@LoadBalanced
	private OAuth2RestTemplate oauth2RestTemplate;

	private MockHttpServletRequest request = new MockHttpServletRequest();

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@After
	public void clean() {
		RequestContextHolder.resetRequestAttributes();
	}*/

	@Test
	@Ignore
	public void oauth2RestTemplateHasLoadBalancer() throws Exception {
	/*	// Just to prove that the interceptor is present...
		ClientHttpRequest request = oauth2RestTemplate.getRequestFactory()
				.createRequest(new URI("http://nosuchservice"), HttpMethod.GET);
		expected.expectMessage("No instances available for nosuchservice");
		request.execute();*/
	}

	/*@Test
	public void useRestTemplate() throws Exception {
		// There's nowhere to get an access token so it should fail, but in a sensible way
		this.expected.expect(UserRedirectRequiredException.class);
		RequestContextHolder
				.setRequestAttributes(new ServletRequestAttributes(this.request));
		this.oauth2RestTemplate.getForEntity("http://foo/bar", String.class);
	}*/

}
