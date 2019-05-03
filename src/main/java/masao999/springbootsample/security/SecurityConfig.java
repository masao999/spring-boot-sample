package masao999.springbootsample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

/**
 * セキュリティ設定クラス
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 認証が成功した場合のクラス
     */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 認証が失敗した場合のクラス
     */
    private final AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 未認証のユーザが認証の必要なAPIにアクセスした場合のクラス
     */
    private final AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * ログアウトが成功した場合のクラス
     */
    private final LogoutSuccessHandler logoutSuccessHandler;

    /**
     * コンストラクタ
     *
     * @param authenticationSuccessHandler 認証が成功した場合のクラス
     * @param authenticationFailureHandler 認証が失敗した場合のクラス
     * @param authenticationEntryPoint     未認証のユーザが認証の必要なAPIにアクセスした場合のクラス
     * @param logoutSuccessHandler         ログアウトが成功した場合のクラス
     */
    @Autowired
    public SecurityConfig(
            AuthenticationSuccessHandler authenticationSuccessHandler,
            AuthenticationFailureHandler authenticationFailureHandler,
            AuthenticationEntryPoint authenticationEntryPoint,
            LogoutSuccessHandler logoutSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    /**
     * セキュリティ設定
     *
     * @param http HTTP関連のセキュリティ設定
     * @throws Exception 発生した例外
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // login以外は認証されたユーザしか許可しない
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated();

        // 未認証のユーザが認証の必要なAPIにアクセスした場合の処理を設定
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);

        // loginに関する設定
        http.formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        // CSRF対策に関する設定
        http.csrf()
                .ignoringAntMatchers("/login")
                .csrfTokenRepository(csrfTokenRepository());

        // logoutに関する設定
        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler);
    }

    /**
     * 有効なユーザ名とパスワードをメモリ上に設定
     *
     * @param auth 認証管理生成クラス
     * @throws Exception 発生した例外
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("username")
                .password(passwordEncoder().encode("password"))
                .roles("USER");
    }

    /**
     * パスワードエンコーダを取得
     *
     * @return パスワードエンコーダ
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * CsrfTokenRepository取得
     *
     * @return CsrfTokenRepository
     */
    private CsrfTokenRepository csrfTokenRepository() {
        return new CookieCsrfTokenRepository();
    }
}
