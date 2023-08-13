package org.itsci.project.controller;


import org.itsci.project.model.Subject;
import org.itsci.project.model.User;
import org.itsci.project.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/list")
    public ResponseEntity get_ListTeacher (){
        try {
            List<User> users = teacherService.get_ListTeacher();
            return new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list user!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
