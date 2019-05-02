package masao999.springbootsample.service;

import masao999.springbootsample.entity.Directory;

import java.util.List;
import java.util.Optional;

/**
 * list APIのServiceインタフェース
 */
public interface ListService {

    /**
     * directoryテーブルの全行を取得
     *
     * @return directoryテーブルのエンティティリスト
     */
    List<Directory> list();

    /**
     * directoryテーブルの指定されたIDに対応する行を取得
     *
     * @param id ID
     * @return directoryテーブルのエンティティ
     */
    Optional<Directory> listById(final int id);

    /**
     * directoryテーブルの指定された名前に対応する行を追加
     *
     * @param name 名前
     */
    void listAdd(final String name);

    /**
     * directoryテーブルの指定されたIDに対応する名前を更新
     *
     * @param id   ID
     * @param name 名前
     */
    void listUpdate(final int id, final String name);
}
