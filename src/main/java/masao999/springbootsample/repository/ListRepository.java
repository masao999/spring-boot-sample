package masao999.springbootsample.repository;

import masao999.springbootsample.entity.Directory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * list APIのRepositoryインタフェース
 */
@Repository
@Mapper
public interface ListRepository {

    /**
     * directoryテーブルの全行を取得
     *
     * @return directoryテーブルのエンティティリスト
     */
    @Select("SELECT * FROM directory ORDER BY id")
    List<Directory> list();

    /**
     * directoryテーブルの指定されたIDに対応する行を取得
     *
     * @param id ID
     * @return directoryテーブルのエンティティ
     */
    @Select("SELECT * FROM directory WHERE id = #{id}")
    Optional<Directory> listById(final int id);

    /**
     * directoryテーブルの指定された名前に対応する行を追加
     *
     * @param name 名前
     */
    @Insert("INSERT INTO directory (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true)
    void listAdd(final String name);

    /**
     * directoryテーブルの指定されたIDに対応する名前を更新
     *
     * @param id   ID
     * @param name 名前
     */
    @Update("UPDATE directory SET name = #{name} WHERE id = #{id}")
    void listUpdate(
            @Param("id") final int id,
            @Param("name") final String name);
}
