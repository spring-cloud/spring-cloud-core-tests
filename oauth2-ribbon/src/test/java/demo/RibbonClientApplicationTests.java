package demo;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RibbonClientApplication.class)
@IntegrationTest("debug=true")
@WebAppConfiguration
public class RibbonClientApplicationTests {

	@Autowired
	@LoadBalanced
	private OAuth2RestTemplate oauth2RestTemplate;

	@Autowired
	@Qualifier("loadBalancedRestTemplate")
	@LoadBalanced
	private RestTemplate restTemplate;

	private MockHttpServletRequest request = new MockHttpServletRequest();

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@After
	public void clean() {
		RequestContextHolder.resetRequestAttributes();
	}

	@Test
	public void restTemplateHasLoadBalancer() {
		// Just to prove that the request factory is present...
		assertThat(this.restTemplate.getRequestFactory(),
				instanceOf(InterceptingClientHttpRequestFactory.class));
		assertThat(ReflectionTestUtils.getField(this.restTemplate.getRequestFactory(), "requestFactory"),
				instanceOf(RibbonClientHttpRequestFactory.class));

	}

	@Test
	public void oauth2RestTemplateHasLoadBalancer() {
		// Just to prove that the interceptor is present...
		assertThat(new ArrayList<Object>(this.oauth2RestTemplate.getInterceptors()),
				hasItem(instanceOf(LoadBalancerInterceptor.class)));
	}

	@Test
	public void useRestTemplate() throws Exception {
		// There's nowhere to get an access token so it should fail, but in a sensible way
		this.expected.expect(UserRedirectRequiredException.class);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(this.request));
		this.oauth2RestTemplate.getForEntity("http://foo/bar", String.class);
	}

}
