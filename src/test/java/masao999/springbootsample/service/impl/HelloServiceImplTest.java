package masao999.springbootsample.service.impl;

import masao999.springbootsample.repository.HelloRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

/**
 * {@link HelloServiceImpl}のテストケース
 */
public class HelloServiceImplTest {

    /**
     * hello APIのService実装クラス
     */
    private HelloServiceImpl helloService;

    /**
     * hello APIのRepositoryのモック
     */
    @Mock
    private HelloRepository mockHelloRepository;

    /**
     * 事前処理
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        helloService = new HelloServiceImpl(mockHelloRepository);
    }

    /**
     * {@link HelloServiceImpl#hello()}のテストケース
     */
    @Test
    public void testHello() {
        when(mockHelloRepository.hello()).thenReturn("hello");

        assertThat(helloService.hello(), is("hello"));
    }
}
