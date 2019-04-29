package masao999.springbootsample.security.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ログアウトが成功した場合の実装クラス
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    /**
     * ログアウトが成功した場合の処理
     *
     * @param request        リクエスト
     * @param response       レスポンス
     * @param authentication 認証クラス
     * @throws IOException 入出力時に発生した例外
     */
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication)
            throws IOException {
        if (authentication != null) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
    }
}
