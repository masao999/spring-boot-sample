package masao999.springbootsample.repository;

/**
 * hello APIのRepositoryクラス
 */
public interface HelloRepository {

    /**
     * レスポンス文字列を返す
     *
     * @return レスポンス文字列
     */
    String hello();
}
