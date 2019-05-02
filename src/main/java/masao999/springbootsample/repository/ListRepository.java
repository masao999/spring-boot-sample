package masao999.springbootsample.repository;

import masao999.springbootsample.entity.Directory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
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
}
