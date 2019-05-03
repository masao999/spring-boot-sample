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
     * {@link ListUpdateRequestDto#setBeforeName(String)}と{@link ListUpdateRequestDto#getBeforeName()}のテストケース
     */
    @Test
    public void testSetGetBeforeName() {
        listUpdateRequestDto.setBeforeName("hoge");
        assertThat(listUpdateRequestDto.getBeforeName(), is("hoge"));
    }

    /**
     * {@link ListUpdateRequestDto#setAfterName(String)}と{@link ListUpdateRequestDto#getAfterName()}のテストケース
     */
    @Test
    public void testSetGetAfterName() {
        listUpdateRequestDto.setAfterName("hoge");
        assertThat(listUpdateRequestDto.getAfterName(), is("hoge"));
    }
}
