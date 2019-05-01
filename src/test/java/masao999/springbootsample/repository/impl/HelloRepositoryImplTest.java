package masao999.springbootsample.repository.impl;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * {@link HelloRepositoryImpl}のテストケース
 */
public class HelloRepositoryImplTest {

    /**
     * hello APIのRepository実装クラス
     */
    private HelloRepositoryImpl helloRepository;

    /**
     * 事前処理
     */
    @Before
    public void setUp() {
        helloRepository = new HelloRepositoryImpl();
    }

    /**
     * {@link HelloRepositoryImpl#hello()}のテストケース
     */
    @Test
    public void testHello() {
        assertThat(helloRepository.hello(), is("hello"));
    }
}
