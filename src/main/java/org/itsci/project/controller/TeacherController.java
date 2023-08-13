package org.itsci.project.controller;


import org.itsci.project.model.Subject;
import org.itsci.project.model.User;
import org.itsci.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserService teacherService;

    //listTeacher
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

    //getid
    @RequestMapping("/getbyid/{id}")
    public ResponseEntity get_Teacher (@PathVariable("id") String id){
        try {
            User users = teacherService.get_Teacher(id);
            return new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity add_Teacher(@RequestBody Map<String,String> map){
        try {
            User user = teacherService.add_Teacher(map);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Subject!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
