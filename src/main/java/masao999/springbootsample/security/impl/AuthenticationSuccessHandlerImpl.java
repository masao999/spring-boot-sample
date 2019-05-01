package masao999.springbootsample.security.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 認証が成功した場合の実装クラス
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    /**
     * 認証が成功した場合の処理
     *
     * @param request        リクエスト
     * @param response       レスポンス
     * @param authentication 認証クラス
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) {
        response.setStatus(HttpStatus.OK.value());
    }
}
