package org.itsci.project.controller;

import org.itsci.project.model.User;
import org.itsci.project.repository.TeacherRepository;
import org.itsci.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //get by username
//    @RequestMapping("/getbyusername/{username}")
//    public ResponseEntity get_User (@PathVariable("username") String username){
//        try {
//            User user = userRepository.getUserByUsernameEquals(username);
//            return new ResponseEntity<>(user , HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>("Failed to get User!", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
