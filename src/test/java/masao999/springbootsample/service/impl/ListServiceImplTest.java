package masao999.springbootsample.service.impl;

import masao999.springbootsample.entity.Directory;
import masao999.springbootsample.repository.ListRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Directory directory = new Directory();
        directory.setId(1);
        directory.setName("hoge");
        List<Directory> directoryList = new ArrayList<>();
        directoryList.add(directory);
        when(mockListRepository.list()).thenReturn(directoryList);

        assertThat(listService.list().size(), is(1));
        assertThat(listService.list().get(0).getId(), is(1));
        assertThat(listService.list().get(0).getName(), is("hoge"));
    }

    /**
     * {@link ListServiceImpl#listById(int)}のテストケース
     */
    @Test
    public void testListById() {
        Directory directory = new Directory();
        directory.setId(1);
        directory.setName("hoge");
        when(mockListRepository.listById(anyInt())).thenReturn(Optional.of(directory));

        assertThat(listService.listById(1).map(Directory::getId).get(), is(1));
        assertThat(listService.listById(1).map(Directory::getName).get(), is("hoge"));
    }
}
