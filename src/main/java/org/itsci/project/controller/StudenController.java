package org.itsci.project.controller;

import org.itsci.project.model.User;
import org.itsci.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudenController {

    @Autowired
    private UserService userService;

    //listStudent
    @RequestMapping("/list")
    public ResponseEntity get_ListStudent (){
        try {
            String typeuser = "Student";
            List<User> users = userService.get_ListStudent(typeuser);
            return new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list student!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
