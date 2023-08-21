package org.itsci.project.service;

import org.itsci.project.model.Authority;
import org.itsci.project.model.Login;
import org.itsci.project.model.User;
import org.itsci.project.repository.AuthorityRepository;
import org.itsci.project.repository.LoginRepository;
import org.itsci.project.repository.TeacherRepository;
import org.itsci.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.RowSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Login> get_ListLogin() {
        return loginRepository.findAll();
    }

    @Override
    public Login get_Login(String username) {
        return loginRepository.getReferenceById(username);
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
    public Login addRoleToLogin(Long id, Long userId) {
        Set<Authority> roleSet = null;
        Authority authority = authorityRepository.findById(id).get();
        Login login = loginRepository.findById(userId).get();
        roleSet = login.getRole();
        roleSet.add(authority);
        login.setRole(roleSet);
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

    @Override
    public Login do_Login(Map<String,String> map) {
        String username = map.get("username");
        String password = map.get("password");

        //Check username
        Login login = loginRepository.getLoginByUsernameEquals(username);

        if (login != null && login.getPassword().equals(password)){
            return login;
        }else {
            return null;
        }
    }

}
