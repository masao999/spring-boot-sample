package masao999.springbootsample.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * list API(更新)リクエストDTO
 */
public class ListUpdateRequestDto {

    /**
     * 変更前の名前
     */
    @NotNull
    @Size(max = 256)
    private String beforeName;

    /**
     * 変更後の名前
     */
    @NotNull
    @Size(max = 256)
    private String afterName;

    // TODO: lombokが使えない、Java12だから？

    /**
     * 変更前の名前設定
     *
     * @param beforeName 変更前の名前
     */
    public void setBeforeName(final String beforeName) {
        this.beforeName = beforeName;
    }

    /**
     * 変更前の名前取得
     *
     * @return 変更前の名前
     */
    public String getBeforeName() {
        return beforeName;
    }

    /**
     * 変更後の名前設定
     *
     * @param afterName 変更後の名前
     */
    public void setAfterName(final String afterName) {
        this.afterName = afterName;
    }

    /**
     * 変更後の名前取得
     *
     * @return 変更後の名前
     */
    public String getAfterName() {
        return afterName;
    }
}
