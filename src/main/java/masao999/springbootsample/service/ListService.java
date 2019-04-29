package masao999.springbootsample.service;

import masao999.springbootsample.entity.Sample;

import java.util.List;

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
}
