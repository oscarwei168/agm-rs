package tw.com.agm.rs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
@TestPropertySource("/test.properties")
public class HelloWorldTests {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGreeting() throws Exception {
//	HttpHeaders headers = new HttpHeaders();
//	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//	MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//	ResponseEntity<String> entity = restTemplate
//		.postForEntity("http://localhost:" + this.port + "/agm-rs/api/health/call", request, String.class);
	ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + "/agm-rs/api/health/call",
		String.class);
	assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}