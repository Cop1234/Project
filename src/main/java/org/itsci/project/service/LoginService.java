package org.itsci.project.service;

import org.itsci.project.model.Login;

import java.util.List;
import java.util.Map;

public interface LoginService {

    List<Login> get_ListLogin ();

    //GET BY ID
    Login get_Login(String username);

    //CREATE
    Login add_Login(Map<String,String> map);

    //Update
    Login update_Login (Login login);

    //Delete
    void delet_Login(String id);

    //GET BY CONTAINING NAME
    List<Login> getLoginByUsernameContainingIgnoreCase (String username);

    //Login
    Login do_Login(Map<String,String> map);

    Login addRoleToLogin(Long id, Long userId);
}
