package masao999.springbootsample.security.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未認証のユーザが認証の必要なAPIにアクセスした場合の実装クラス
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    /**
     * 未認証のユーザが認証の必要なAPIにアクセスした場合の処理
     *
     * @param request リクエスト
     * @param response レスポンス
     * @param authException 認証時に発生した例外
     * @throws IOException 入出力時に発生した例外
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
}
