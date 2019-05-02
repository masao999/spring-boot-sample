package masao999.springbootsample.entity;

/**
 * directoryテーブルのエンティティクラス
 */
public class Directory {

    /**
     * ID
     */
    private int id;

    /**
     * 名前
     */
    private String name;

    // TODO: lombokが使えない、Java12だから？

    /**
     * ID設定
     *
     * @param id ID
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * ID取得
     *
     * @return ID
     */
    public int getId() {
        return id;
    }


    /**
     * 名前設定
     *
     * @param name 名前
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 名前取得
     *
     * @return 名前
     */
    public String getName() {
        return name;
    }
}
