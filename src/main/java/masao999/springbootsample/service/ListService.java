package masao999.springbootsample.service;

import masao999.springbootsample.entity.Sample;

import java.util.List;
import java.util.Optional;

/**
 * list APIのServiceインタフェース
 */
public interface ListService {

    /**
     * sampleテーブルの全行を取得
     *
     * @return sampleテーブルのエンティティリスト
     */
    List<Sample> list();

    /**
     * sampleテーブルの指定されたIDに対応する行を取得
     *
     * @param id ID
     * @return sampleテーブルのエンティティ
     */
    Optional<Sample> listById(final int id);
}
