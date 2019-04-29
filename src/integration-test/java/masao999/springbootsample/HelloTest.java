package masao999.springbootsample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * hello APIのテストケース
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private HttpHeaders headers;

    @Before
    @SuppressWarnings(value = {"all"})
    public void setUp() {
        final String body = "username=username&password=password";
        HttpHeaders headersForLogin = new HttpHeaders();
        headersForLogin.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ResponseEntity response = testRestTemplate.exchange(
                "/login",
                HttpMethod.POST,
                new HttpEntity<>(body, headersForLogin),
                String.class);
        headers = new HttpHeaders();
        headers.add("Cookie", response.getHeaders().get("Set-Cookie").get(0));
    }

    /**
     * hello APIのテストケース
     */
    @Test
    @SuppressWarnings(value = {"ConstantConditions"})
    public void testHello() {
        ResponseEntity responseBeforeLogout = testRestTemplate.exchange(
                "/hello",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseBeforeLogout.getBody().toString(), is("hello"));
        assertThat(responseBeforeLogout.getStatusCode(), is(HttpStatus.OK));
    }

    /**
     * hello APIの未認証テストケース
     */
    @Test
    public void testHelloUnauthorized() {
        ResponseEntity responseBeforeLogout = testRestTemplate.exchange(
                "/hello",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);
        assertThat(responseBeforeLogout.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }
}
