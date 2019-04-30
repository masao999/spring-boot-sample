package masao999.springbootsample.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ユーザ情報を集めるクラス
 */
@Component
public class UserInformationCollector {

    /**
     * ELB経由のアクセスの際のリモートアドレス格納先ヘッダ名
     */
    private static final String REMOTE_ADDR_FOR_ELB = "X-Forwarded-For";

    /**
     * ユーザ名を取得
     *
     * @return ユーザ名
     */
    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? ((User) authentication.getPrincipal()).getUsername() : "anonymous";
    }

    /**
     * ユーザのリモートアドレスを取得する
     *
     * @return リモートアドレス
     */
    public String getRemoteAddr() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return "unknown";
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (request == null) {
            return "unknown";
        }
        // ELB経由のアクセスの場合に対応
        if (request.getHeader(REMOTE_ADDR_FOR_ELB) != null) {
            return request.getHeader(REMOTE_ADDR_FOR_ELB);
        }
        // ELB経由のアクセスではない場合は通常の方法で取得
        if (request.getRemoteAddr() != null) {
            return request.getRemoteAddr();
        }
        // それでも取得できない場合は"unknown"を返す
        return "unknown";
    }
}
