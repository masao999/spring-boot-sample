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
        headers.add("Cookie", response.getHeaders().get("Set-Cookie").get(1));
        // substringでトークンの箇所のみ抜き出す
        headers.set("X-XSRF-TOKEN", response.getHeaders().get("Set-Cookie").get(0).substring(11, 47));
        headers.setContentType(MediaType.APPLICATION_JSON);
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

    /**
     * ログアウト処理の未認証テストケース
     */
    @Test
    @SuppressWarnings(value = {"ConstantConditions"})
    public void testLogoutUnauthorized() {

        // ログアウトするがヘッダにCookieを設定していないので失敗
        ResponseEntity response = testRestTemplate.exchange(
                "/logout",
                HttpMethod.POST,
                HttpEntity.EMPTY,
                String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));

        // 認証が必要なAPIがまだ呼び出せる
        ResponseEntity responseAfterLogout = testRestTemplate.exchange(
                "/hello",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseAfterLogout.getBody().toString(), is("hello"));
        assertThat(responseAfterLogout.getStatusCode(), is(HttpStatus.OK));
    }
}
