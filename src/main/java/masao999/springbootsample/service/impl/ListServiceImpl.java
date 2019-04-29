package masao999.springbootsample.service.impl;

import masao999.springbootsample.entity.Sample;
import masao999.springbootsample.repository.ListRepository;
import masao999.springbootsample.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * list APIのService実装クラス
 */
@Service
public class ListServiceImpl implements ListService {

    /**
     * list APIのRepository
     */
    private final ListRepository listRepository;

    /**
     * コンストラクタ
     *
     * @param listRepository list APIのRepository
     */
    @Autowired
    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    /**
     * sampleテーブルの全行を取得
     *
     * @return sampleテーブルのエンティティリスト
     */
    public List<Sample> list() {
        return listRepository.list();
    }
}
