package masao999.springbootsample.repository.impl;

import masao999.springbootsample.repository.LoginRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

    public String login(final String username, final String password) {
        // 認証処理は暫定
        if (!"hoge".equals(password)) {
            throw new AuthenticationCredentialsNotFoundException("login NG");
        }
        return username;
    }
}
