package org.itsci.project.controller;


import org.itsci.project.model.Login;
import org.itsci.project.model.Subject;
import org.itsci.project.model.User;
import org.itsci.project.repository.TeacherRepository;
import org.itsci.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserService userService;

    //listTeacher
    @RequestMapping("/list")
    public ResponseEntity get_ListTeacher (){
        try {
            String typeuser = "Teacher";
            List<User> users = userService.get_ListTeacher(typeuser);
            return new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to list teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //getid
    @RequestMapping("/getbyid/{id}")
    public ResponseEntity get_Teacher (@PathVariable("id") String id){
        try {
            User users = userService.get_Teacher(id);
            return new ResponseEntity<>(users , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity add_Teacher(@RequestBody Map<String,String> map){
        try {
            User user = userService.add_Teacher(map);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Add Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //ubdate Teacher
    @PutMapping("/update")
    public ResponseEntity update_Teacher(@RequestBody Map<String,String> map) {
        try {
            User user = userService.update_Teacher(map);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    delete subject
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delet_Teacher (@PathVariable("id") String id){
        try {
            //เรียก SubjectById
            User users = userService.get_Teacher(id);
            String us = users.getUserid();
            //ลบ
            userService.delet_Teacher(id);

            return new ResponseEntity<>("User " + us + " was deleted!", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete User by id!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //หาข้อมูลทั้งหมดด้วยตัวอักษร
    @GetMapping("/getbycontname/{fname}")
    public ResponseEntity getTeacherByfnameContainingIgnoreCase (@PathVariable("fname") String fname){
        try {
            List<User> users = userService.getTeacherByfnameContainingIgnoreCase(fname);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to getUser by name", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updatepassword")
    public ResponseEntity PasswordEncoderTeacher(@RequestBody Map<String,String> map) {
        try {
            Login login = userService.UpdateTeacherProfile(map);
            return new ResponseEntity<>(login, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update Teacher!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
