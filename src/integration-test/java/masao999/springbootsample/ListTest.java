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
        headers.add("Cookie", response.getHeaders().get("Set-Cookie").get(0));
        headers.add("Cookie", response.getHeaders().get("Set-Cookie").get(1));
        // substringでトークンの箇所のみ抜き出す
        headers.set("X-XSRF-TOKEN", response.getHeaders().get("Set-Cookie").get(0).substring(11, 47));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * list API(全行参照)のテストケース
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
     * list API(1行参照)のテストケース
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
     * list API(追加)のテストケース
     */
    @Test
    @SuppressWarnings(value = {"ConstantConditions"})
    public void testLisAdd() {
        final String body = "{\"name\":\"four\"}";
        ResponseEntity response = testRestTemplate.exchange(
                "/list",
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        ResponseEntity responseAfterAdd = testRestTemplate.exchange(
                "/list",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseAfterAdd.getBody().toString(), is("{\"response\":[\"one\",\"two\",\"three\",\"four\"]}"));
        assertThat(responseAfterAdd.getStatusCode(), is(HttpStatus.OK));
    }

    /**
     * list API(更新)のテストケース
     */
    @Test
    @SuppressWarnings(value = {"ConstantConditions"})
    public void testLisUpdate() {
        final String body = "{\"id\":3,\"name\":\"hoge\"}";
        ResponseEntity response = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(body, headers),
                String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));

        ResponseEntity responseAfterAdd = testRestTemplate.exchange(
                "/list",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseAfterAdd.getBody().toString(), is("{\"response\":[\"one\",\"two\",\"hoge\"]}"));
        assertThat(responseAfterAdd.getStatusCode(), is(HttpStatus.OK));

        final String bodyRestore = "{\"id\":3,\"name\":\"three\"}";
        ResponseEntity responseRestore = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyRestore, headers),
                String.class);
        assertThat(responseRestore.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    /**
     * hello API(全行参照)の未認証テストケース
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
     * hello API(1行参照)の未認証テストケース
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
     * hello API(追加)の未認証テストケース
     */
    @Test
    public void testListAddUnauthorized() {
        final String body = "{\"name\":\"four\"}";
        ResponseEntity response = testRestTemplate.exchange(
                "/list",
                HttpMethod.POST,
                new HttpEntity<>(body, null),
                String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }

    /**
     * hello API(更新)の未認証テストケース
     */
    @Test
    public void testListUpdateUnauthorized() {
        final String body = "{\"id\":3,\"name\":\"hoge\"}";
        ResponseEntity response = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(body, null),
                String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
    }

    /**
     * hello API(1行参照)の対応する行がない場合のテストケース
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
     * hello API(1行参照)のバリデーションNGテストケース
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

        // Min(1)と等しい場合
        ResponseEntity responseEqualMin = testRestTemplate.exchange(
                "/list/1",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseEqualMin.getStatusCode(), is(HttpStatus.OK));

        // Max(9999)と等しい場合
        ResponseEntity responseEqualMax = testRestTemplate.exchange(
                "/list/9999",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                String.class);
        assertThat(responseEqualMax.getStatusCode(), is(HttpStatus.OK));

        // Max(9999)を上回る場合
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

    /**
     * hello API(追加)のバリデーションNGテストケース
     */
    @Test
    public void testLisAddValidationNg() {

        // @Size(max = 256)と等しい場合
        final String body256 = "{\"name\":\"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456\"}";
        ResponseEntity response256 = testRestTemplate.exchange(
                "/list",
                HttpMethod.POST,
                new HttpEntity<>(body256, headers),
                String.class);
        assertThat(response256.getStatusCode(), is(HttpStatus.CREATED));

        // @Size(max = 256)を上回る場合
        final String body257 = "{\"name\":\"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\"}";
        ResponseEntity response257 = testRestTemplate.exchange(
                "/list",
                HttpMethod.POST,
                new HttpEntity<>(body257, headers),
                String.class);
        assertThat(response257.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        // nameの指定がない場合
        final String bodyNoName = "{\"hoge\":\"four\"}";
        ResponseEntity responseNoName = testRestTemplate.exchange(
                "/list",
                HttpMethod.POST,
                new HttpEntity<>(bodyNoName, headers),
                String.class);
        assertThat(responseNoName.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }

    /**
     * hello API(更新)のバリデーションNGテストケース
     */
    @Test
    public void testLisUpdateValidationNg() {

        // IDがMin(1)を下回る場合
        final String bodyIdLessThanMin = "{\"id\":0,\"name\":\"hoge\"}";
        ResponseEntity responseIdLessThanMin = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyIdLessThanMin, headers),
                String.class);
        assertThat(responseIdLessThanMin.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        // IDがMin(1)と等しい場合
        final String bodyIdEqualMin = "{\"id\":1,\"name\":\"hoge\"}";
        ResponseEntity responseIdEqualMin = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyIdEqualMin, headers),
                String.class);
        assertThat(responseIdEqualMin.getStatusCode(), is(HttpStatus.NO_CONTENT));

        final String bodyIdEqualMinRestore = "{\"id\":1,\"name\":\"one\"}";
        ResponseEntity responseIdEqualMinRestore = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyIdEqualMinRestore, headers),
                String.class);
        assertThat(responseIdEqualMinRestore.getStatusCode(), is(HttpStatus.NO_CONTENT));

        // IDがMax(9999)と等しい場合
        final String bodyIdEqualMax = "{\"id\":9999,\"name\":\"hoge\"}";
        ResponseEntity responseIdEqualMax = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyIdEqualMax, headers),
                String.class);
        assertThat(responseIdEqualMax.getStatusCode(), is(HttpStatus.NO_CONTENT));

        // IDがMax(9999)を上回る場合
        final String bodyIdExceedMax = "{\"id\":10000,\"name\":\"hoge\"}";
        ResponseEntity responseIdExceedMax = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyIdExceedMax, headers),
                String.class);
        assertThat(responseIdExceedMax.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        // IDが数字ではない
        final String bodyIdNotNumber = "{\"id\":\"hoge\",\"name\":\"hoge\"}";
        ResponseEntity responseIdNotNumber = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyIdNotNumber, headers),
                String.class);
        assertThat(responseIdNotNumber.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        // IDの指定がない場合
        final String bodyNoId = "{\"name\":\"hoge\"}";
        ResponseEntity responseNoId = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyNoId, headers),
                String.class);
        assertThat(responseNoId.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        // 名前が@Size(max = 256)と等しい場合
        final String bodyName256 = "{\"id\":3,\"name\":\"1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456\"}";
        ResponseEntity responseName256 = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyName256, headers),
                String.class);
        assertThat(responseName256.getStatusCode(), is(HttpStatus.NO_CONTENT));

        final String bodyName256Restore = "{\"id\":3,\"name\":\"three\"}";
        ResponseEntity responseName256Restore = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyName256Restore, headers),
                String.class);
        assertThat(responseName256Restore.getStatusCode(), is(HttpStatus.NO_CONTENT));

        // 名前が@Size(max = 256)を上回る場合
        final String bodyName257 = "{\"id\":3,\"name\":\"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567\"}";
        ResponseEntity responseName257 = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyName257, headers),
                String.class);
        assertThat(responseName257.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        // 名前の指定がない場合
        final String bodyNoName = "{\"id\":3}";
        ResponseEntity responseNoName = testRestTemplate.exchange(
                "/list",
                HttpMethod.PUT,
                new HttpEntity<>(bodyNoName, headers),
                String.class);
        assertThat(responseNoName.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }
}
