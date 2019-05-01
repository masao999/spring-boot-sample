package masao999.springbootsample;

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
 * 認証処理のテストケース
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    /**
     * 認証処理の正常系テストケース
     */
    @Test
    @SuppressWarnings(value = {"ConstantConditions"})
    public void testLogin() {
        final String body = "username=username&password=password";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ResponseEntity response = testRestTemplate.exchange(
                "/login",
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                String.class);
        assertThat(response.getHeaders().get("Set-Cookie").get(0).startsWith("XSRF-TOKEN="), is(true));
        assertThat(response.getHeaders().get("Set-Cookie").get(1).startsWith("JSESSIONID="), is(true));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}