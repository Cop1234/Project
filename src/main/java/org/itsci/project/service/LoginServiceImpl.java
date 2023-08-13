package org.itsci.project.service;

import org.itsci.project.model.Login;
import org.itsci.project.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public List<Login> get_ListLogin() {
        return null;
    }

    @Override
    public Login get_Login(String id) {
        return null;
    }

    @Override
    public Login add_Login(Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");

        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        return loginRepository.save(login);
    }

    @Override
    public Login update_Login(Login login) {
        return null;
    }

    @Override
    public void delet_Login(String id) {

    }

    @Override
    public List<Login> getLoginByUsernameContainingIgnoreCase(String username) {
        return null;
    }
}
