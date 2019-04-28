package masao999.springbootsample.security.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 認証が失敗した場合の実装クラス
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    /**
     * 認証が失敗した場合の処理
     *
     * @param request リクエスト
     * @param response レスポンス
     * @param exception 認証時に発生した例外
     * @throws IOException 入出力時に発生した例外
     */
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
}
