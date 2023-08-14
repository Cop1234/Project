package org.itsci.project.controller;

import lombok.extern.java.Log;
import org.itsci.project.model.Login;
import org.itsci.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/list")
    public ResponseEntity get_ListRoom (){
        try {
            List<Login> logins = loginService.get_ListLogin();
            return new ResponseEntity<>(logins , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list Login!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/getbyusername/{username}")
    public ResponseEntity get_Login (@PathVariable("username") String username){
        try {
            Login login = loginService.get_Login(username);
            return new ResponseEntity<>(login , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Login by Username!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    @PostMapping("/do_login")
    public ResponseEntity do_Login(@RequestBody Map<String,String> map){
        try {
            Login login = loginService.do_Login(map);
            if (login != null){
                return new ResponseEntity<>(login, HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Login Failed!", HttpStatus.CONFLICT);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Do Login!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
