package masao999.springbootsample.dto;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * {@link ListUpdateRequestDto}のテストケース
 */
public class ListUpdateRequestDtoTest {

    /**
     * list API(更新)リクエストDTO
     */
    private ListUpdateRequestDto listUpdateRequestDto;

    /**
     * 事前処理
     */
    @Before
    public void setUp() {
        listUpdateRequestDto = new ListUpdateRequestDto();
    }

    /**
     * {@link ListUpdateRequestDto#setId(int)}と{@link ListUpdateRequestDto#getId()}のテストケース
     */
    @Test
    public void testSetGetId() {
        listUpdateRequestDto.setId(123);
        assertThat(listUpdateRequestDto.getId(), is(123));
    }

    /**
     * {@link ListUpdateRequestDto#setName(String)}と{@link ListUpdateRequestDto#getName()}のテストケース
     */
    @Test
    public void testSetGetName() {
        listUpdateRequestDto.setName("hoge");
        assertThat(listUpdateRequestDto.getName(), is("hoge"));
    }
}
