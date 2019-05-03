package masao999.springbootsample.dto;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * {@link ListDeleteRequestDto}のテストケース
 */
public class ListDeleteRequestDtoTest {

    /**
     * list API(削除)リクエストDTO
     */
    private ListDeleteRequestDto listDeleteRequestDto;

    /**
     * 事前処理
     */
    @Before
    public void setUp() {
        listDeleteRequestDto = new ListDeleteRequestDto();
    }

    /**
     * {@link ListDeleteRequestDto#setName(String)}と{@link ListDeleteRequestDto#getName()}のテストケース
     */
    @Test
    public void testSetGetName() {
        listDeleteRequestDto.setName("hoge");
        assertThat(listDeleteRequestDto.getName(), is("hoge"));
    }
}
