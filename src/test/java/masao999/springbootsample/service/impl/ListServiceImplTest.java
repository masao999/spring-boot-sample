package masao999.springbootsample.service.impl;

import masao999.springbootsample.entity.Sample;
import masao999.springbootsample.repository.ListRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * {@link ListServiceImpl}のテストケース
 */
public class ListServiceImplTest {

    /**
     * List APIのService実装クラス
     */
    private ListServiceImpl listService;

    /**
     * List APIのeRepository実装クラスのモック
     */
    @Mock
    private ListRepository mockListRepository;

    /**
     * 事前処理
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listService = new ListServiceImpl(mockListRepository);
    }

    /**
     * {@link ListServiceImpl#list()}のテストケース
     */
    @Test
    public void testList() {
        Sample sample = new Sample();
        sample.id = 1;
        sample.name = "hoge";
        List<Sample> sampleList = new ArrayList<>();
        sampleList.add(sample);
        when(mockListRepository.list()).thenReturn(sampleList);

        assertThat(listService.list().size(), is(1));
        assertThat(listService.list().get(0).id, is(1));
        assertThat(listService.list().get(0).name, is("hoge"));
    }

    /**
     * {@link ListServiceImpl#listById(int)}のテストケース
     */
    @Test
    public void testListById() {
        Sample sample = new Sample();
        sample.id = 1;
        sample.name = "hoge";
        when(mockListRepository.listById(anyInt())).thenReturn(sample);

        assertThat(listService.listById(1).id, is(1));
        assertThat(listService.listById(1).name, is("hoge"));
    }
}
