package masao999.springbootsample.service.impl;

import masao999.springbootsample.entity.Directory;
import masao999.springbootsample.repository.ListRepository;
import masao999.springbootsample.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * directoryテーブルの全行を取得
     *
     * @return directoryテーブルのエンティティリスト
     */
    public List<Directory> list() {
        return listRepository.list();
    }

    /**
     * directoryテーブルの指定されたIDに対応する行を取得
     *
     * @param id ID
     * @return directoryテーブルのエンティティ
     */
    public Optional<Directory> listById(final int id) {
        return listRepository.listById(id);
    }

    /**
     * directoryテーブルの指定された名前に対応する行を追加
     *
     * @param name 名前
     */
    public void listAdd(final String name) {
        listRepository.listAdd(name);
    }
}
