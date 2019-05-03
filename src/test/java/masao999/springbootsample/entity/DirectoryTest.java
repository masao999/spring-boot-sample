package masao999.springbootsample.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * {@link Directory}のテストケース
 */
public class DirectoryTest {

    /**
     * directoryテーブルのエンティティクラス
     */
    private Directory directory;

    /**
     * 事前処理
     */
    @Before
    public void setUp() {
        directory = new Directory();
    }

    /**
     * {@link Directory#setId(int)}と{@link Directory#getId()}のテストケース
     */
    @Test
    public void testSetGetId() {
        directory.setId(123);
        assertThat(directory.getId(), is(123));
    }

    /**
     * {@link Directory#setName(String)}と{@link Directory#getName()}のテストケース
     */
    @Test
    public void testSetGetName() {
        directory.setName("hoge");
        assertThat(directory.getName(), is("hoge"));
    }
}
