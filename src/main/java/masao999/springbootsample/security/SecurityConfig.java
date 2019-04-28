package masao999.springbootsample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

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
     * コンストラクタ
     *
     * @param authenticationSuccessHandler 認証が成功した場合のクラス
     * @param authenticationFailureHandler 認証が失敗した場合のクラス
     * @param authenticationEntryPoint 未認証のユーザが認証の必要なAPIにアクセスした場合のクラス
     */
    @Autowired
    public SecurityConfig(
            AuthenticationSuccessHandler authenticationSuccessHandler,
            AuthenticationFailureHandler authenticationFailureHandler,
            AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    /**
     * セキュリティ設定
     *
     * @param http HTTP関連のセキュリティ設定
     * @throws Exception 発生した例外
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);

        http.formLogin()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        // TODO: CSRFは暫定で無効にしておく
        http.csrf().disable();
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
                .withUser("username").password("password").roles("USER");
    }

    /**
     * パスワードエンコーダを取得
     *
     * @return パスワードエンコーダ
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        // TODO: 別のエンコーダに変更が必要
        return NoOpPasswordEncoder.getInstance();
    }
}
