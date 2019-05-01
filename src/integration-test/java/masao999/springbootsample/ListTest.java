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
 * list APIのテストケース
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListTest {

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
        headers.add("Cookie", response.getHeaders().get("Set-Cookie").get(1));
    }

    /**
     * list APIのテストケース
     */
    @Test
    @SuppressWarnings(value = {"ConstantConditions"})
    public void testList() {
        ResponseEntity response = testRestTemplate.exchange(
                "/list",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(response.getBody().toString(), is("{\"response\":[\"one\",\"two\",\"three\"]}"));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    /**
     * list API(ID付)のテストケース
     */
    @Test
    @SuppressWarnings(value = {"ConstantConditions"})
    public void testListById() {
        ResponseEntity response = testRestTemplate.exchange(
                "/list/1",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(response.getBody().toString(), is("{\"response\":\"one\"}"));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    /**
     * hello APIの未認証テストケース
     */
    @Test
    public void testListUnauthorized() {
        ResponseEntity response = testRestTemplate.exchange(
                "/list",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }

    /**
     * hello API(ID付)の未認証テストケース
     */
    @Test
    public void testListByIdUnauthorized() {
        ResponseEntity response = testRestTemplate.exchange(
                "/list/1",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }

    /**
     * hello API(ID付)の対応する行がない場合のテストケース
     */
    @Test
    @SuppressWarnings(value = {"ConstantConditions"})
    public void testListByIdNothing() {
        ResponseEntity response = testRestTemplate.exchange(
                "/list/1000",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(response.getBody().toString(), is("{}"));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    /**
     * hello API(ID付)のバリデーションNGテストケース
     */
    @Test
    public void testListByIdValidationNg() {

        // Min(1)を下回る場合
        ResponseEntity responseLessThanMin = testRestTemplate.exchange(
                "/list/0",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseLessThanMin.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        // Max(9999)を下回る場合
        ResponseEntity responseExceedMax = testRestTemplate.exchange(
                "/list/10000",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseExceedMax.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        // IDが数字ではない
        ResponseEntity responseNotNumber = testRestTemplate.exchange(
                "/list/hoge",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseNotNumber.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }
}
