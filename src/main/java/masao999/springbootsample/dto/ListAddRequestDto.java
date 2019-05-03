package masao999.springbootsample.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * list API(追加)リクエストDTO
 */
public class ListAddRequestDto {

    /**
     * 名前
     */
    @NotNull
    @Size(max = 256)
    private String name;

    // TODO: lombokが使えない、Java12だから？

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
