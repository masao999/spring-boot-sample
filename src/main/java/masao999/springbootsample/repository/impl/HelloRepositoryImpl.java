package masao999.springbootsample.repository.impl;

import masao999.springbootsample.repository.HelloRepository;
import org.springframework.stereotype.Repository;

/**
 * hello APIのRepository実装クラス
 */
@Repository
public class HelloRepositoryImpl implements HelloRepository {

    /**
     * レスポンス文字列を返す
     *
     * @return レスポンス文字列
     */
    public String hello() {
        return "hello";
    }
}
