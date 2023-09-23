package org.itsci.project.controller;

import org.itsci.project.model.User;
import org.itsci.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //get by username
    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<User> findUserIdByUsername(@PathVariable("username") String username){
        try {
            Long Id = userService.findUserIdByUsername(username);
            User user = userService.get_user(Id);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity("Failed to getUser by name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
