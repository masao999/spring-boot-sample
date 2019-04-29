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
 * ログアウト処理のテストケース
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogoutTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private HttpHeaders headers;

    @Before
    @SuppressWarnings(value = {"ConstantConditions"})
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
     * ログアウト処理のテストケース
     */
    @Test
    @SuppressWarnings(value = {"ConstantConditions"})
    public void testLogout() {

        // 認証時は認証が必要なAPIを呼び出せる
        ResponseEntity responseBeforeLogout = testRestTemplate.exchange(
                "/hello",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseBeforeLogout.getBody().toString(), is("hello"));
        assertThat(responseBeforeLogout.getStatusCode(), is(HttpStatus.OK));

        // ログアウトする
        ResponseEntity responseLogout = testRestTemplate.exchange(
                "/logout",
                HttpMethod.POST,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseLogout.getStatusCode(), is(HttpStatus.OK));

        // 認証が必要なAPIを呼び出せなくなる
        ResponseEntity responseAfterLogout = testRestTemplate.exchange(
                "/hello",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseAfterLogout.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }
}
