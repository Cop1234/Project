package org.itsci.project.controller;

import lombok.extern.java.Log;
import org.itsci.project.model.Login;
import org.itsci.project.repository.LoginRepository;
import org.itsci.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.management.relation.Role;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginRepository loginRepository;

    @RequestMapping("/list")
    public ResponseEntity get_ListLogin (){
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

    @PutMapping("/{id}/login_aut/{userId}")
    public Login addRoleToLogin(
            @PathVariable Long id,
            @PathVariable Long userId
    ){
        return loginService.addRoleToLogin(id,userId);
    }

    @GetMapping("/getrole/{id}")
    public ResponseEntity<Login> getRoleByLoginId(@PathVariable("id") long id) {
        try {
            Optional<Login> loginOptional = loginRepository.findById(id);

            if (loginOptional.isPresent()) {
                Login login = loginOptional.get();
                // You can then return the roles or process them as needed
                return new ResponseEntity(login, HttpStatus.OK);
            } else {
                return new ResponseEntity("Login not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Failed to Get Login by Id!", HttpStatus.INTERNAL_SERVER_ERROR);
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

    @PostMapping("/change_password")
    public ResponseEntity change_Password(@RequestBody Map<String,String> map){
        try {
            Login login = loginService.change_Password(map);
            if (login != null){
                return new ResponseEntity<>(login, HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Check Password Failed!", HttpStatus.CONFLICT);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Check Password!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
