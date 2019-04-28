package masao999.springbootsample.security.impl;

import masao999.springbootsample.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final LoginService loginService;

    @Autowired
    public AuthenticationProviderImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        final String username = auth.getName();
        final String password = auth.getCredentials().toString();

        final String principal = loginService.login(username, password);

        return new UsernamePasswordAuthenticationToken(
                principal, null, null);
    }

    @Override
    public boolean supports(Class<?> token) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(token);
    }
}
