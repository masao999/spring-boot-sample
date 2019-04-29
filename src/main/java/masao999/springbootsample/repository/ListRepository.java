package masao999.springbootsample.repository;

import masao999.springbootsample.entity.Sample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
