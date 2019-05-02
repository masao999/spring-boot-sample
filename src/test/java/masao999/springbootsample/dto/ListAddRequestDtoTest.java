package masao999.springbootsample.dto;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * {@link ListAddRequestDto}のテストケース
 */
public class ListAddRequestDtoTest {

    /**
     * list API(更新)リクエストDTO
     */
    private ListAddRequestDto listAddRequestDto;

    /**
     * 事前処理
     */
    @Before
    public void setUp() {
        listAddRequestDto = new ListAddRequestDto();
    }

    /**
     * {@link ListAddRequestDto#setName(String)}と{@link ListAddRequestDto#getName()}のテストケース
     */
    @Test
    public void testSetGetName() {
        listAddRequestDto.setName("hoge");
        assertThat(listAddRequestDto.getName(), is("hoge"));
    }
}
