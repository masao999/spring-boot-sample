package masao999.springbootsample.service.impl;

import masao999.springbootsample.repository.LoginRepository;
import masao999.springbootsample.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String login(final String username, final String password) {
        return loginRepository.login(username, password);
    }
}
