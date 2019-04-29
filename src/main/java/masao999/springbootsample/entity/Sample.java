package masao999.springbootsample.entity;

import lombok.Data;

/**
 * sampleテーブルのエンティティクラス
 */
@Data
public class Sample {

    /**
     * ID
     */
    private int id;

    /**
     * 名前
     */
    private String name;
}
