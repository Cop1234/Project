package org.itsci.project.controller;

import org.itsci.project.model.Login;
import org.itsci.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/add")
    public ResponseEntity add_Login(@RequestBody Map<String,String> map){
        try {
            Login login = loginService.add_Login(map);
            return new ResponseEntity<>(login, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Login!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
