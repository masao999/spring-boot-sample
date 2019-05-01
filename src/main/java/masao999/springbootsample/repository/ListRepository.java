package masao999.springbootsample.repository;

import masao999.springbootsample.entity.Sample;
import org.apache.ibatis.annotations.Mapper;
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
     * sampleテーブルの全行を取得
     *
     * @return sampleテーブルのエンティティリスト
     */
    @Select("SELECT * FROM sample ORDER BY id")
    List<Sample> list();

    /**
     * sampleテーブルの指定されたIDに対応する行を取得
     *
     * @param id ID
     * @return sampleテーブルのエンティティ
     */
    @Select("SELECT * FROM sample WHERE id = #{id}")
    Optional<Sample> listById(final int id);
}
