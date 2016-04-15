package apps;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@SpringBootApplication
@RestController
public class UiApplication {

	@RequestMapping("/")
	public String home(@RequestParam(required = false) String value) {
		return "Hello " + (value == null ? "World" : value);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder
				.fromCurrentContextPath();
		return "<html><body>"
				+ "<form method='post' enctype='multipart/form-data' action='"
				+ builder.path("/upload").build().toUriString() + "'>"
				+ "File to upload: <input type='file' name='file'>"
				+ "<input type='submit' value='Upload'></form>" + "</body></html>";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String accept(@RequestParam MultipartFile file) throws IOException {
		return new String(file.getBytes());
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(UiApplication.class)
				.properties("spring.config.name:ui").run(args);
	}
}
