package demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "example", url = "http://example.com")
public interface ExampleClient {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String get();
}
